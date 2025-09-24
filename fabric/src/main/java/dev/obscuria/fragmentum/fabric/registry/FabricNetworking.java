package dev.obscuria.fragmentum.fabric.registry;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class FabricNetworking
{
    public static final Map<Class<?>, Registration<?>> clientboundRegistrations = Maps.newConcurrentMap();
    public static final Map<Class<?>, Registration<?>> serverboundRegistrations = Maps.newConcurrentMap();
    public static @Nullable PacketSender clientReplySender;
    public static @Nullable PacketSender serverReplySender;

    public static <T> PacketType<Wrapper<T>> registerClientbound(String modId, Class<T> clazz, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder)
    {
        final var id = new ResourceLocation(modId, "clientbound_" + clazz.getSimpleName().toLowerCase());
        final var type = PacketType.create(id, it -> Wrapper.clientbound(clazz, decoder.apply(it)));
        clientboundRegistrations.put(clazz, new Registration<>(type, encoder));
        return type;
    }

    public static <T> PacketType<Wrapper<T>> registerServerbound(String modId, Class<T> clazz, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder)
    {
        final var id = new ResourceLocation(modId, "serverbound_" + clazz.getSimpleName().toLowerCase());
        final var type = PacketType.create(id, it -> Wrapper.serverbound(clazz, decoder.apply(it)));
        serverboundRegistrations.put(clazz, new Registration<>(type, encoder));
        return type;
    }

    public static FabricPacket clientbound(Object payload)
    {
        Objects.requireNonNull(clientboundRegistrations.get(payload.getClass()));
        return Wrapper.clientbound(payload.getClass(), payload);
    }

    public static FabricPacket serverbound(Object payload)
    {
        Objects.requireNonNull(serverboundRegistrations.get(payload.getClass()));
        return Wrapper.serverbound(payload.getClass(), payload);
    }

    public record Registration<T>(PacketType<Wrapper<T>> packetType, BiConsumer<T, FriendlyByteBuf> encoder)
    {
        @SuppressWarnings("unchecked")
        public void encode(FriendlyByteBuf buf, Object payload)
        {
            encoder.accept((T) payload, buf);
        }
    }

    public record Wrapper<T>(Map<Class<?>, Registration<?>> source, Class<?> type, T payload) implements FabricPacket
    {
        public static <T> Wrapper<T> clientbound(Class<?> type, T payload)
        {
            return new Wrapper<>(clientboundRegistrations, type, payload);
        }

        public static <T> Wrapper<T> serverbound(Class<?> type, T payload)
        {
            return new Wrapper<>(serverboundRegistrations, type, payload);
        }

        @Override
        public void write(FriendlyByteBuf buf)
        {
            source.get(type).encode(buf, payload);
        }

        @Override
        public PacketType<?> getType()
        {
            return source.get(type).packetType;
        }
    }
}
