package dev.obscuria.fragmentum.forge.service;

import com.google.common.collect.Sets;
import dev.obscuria.fragmentum.api.v1.common.IPayloadRegistrar;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.SimpleChannel;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public record ForgePayloadRegistrar(String modId) implements IPayloadRegistrar
{
    private static final Map<String, Integer> discriminatorCounter = new ConcurrentHashMap<>();
    private static final Map<String, SimpleChannel> channels = new ConcurrentHashMap<>();
    private static final Set<String> clientOnlyMods = Sets.newConcurrentHashSet();
    private static final Set<String> serverOnlyMods = Sets.newConcurrentHashSet();

    @Override
    public void allowClientOnly()
    {
        clientOnlyMods.add(modId);
    }

    @Override
    public void allowServerOnly()
    {
        serverOnlyMods.add(modId);
    }

    @Override
    public <T extends CustomPacketPayload> void
    registerClientbound(Class<T> clazz,
                        CustomPacketPayload.Type<T> type,
                        StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
                        BiConsumer<Player, T> handler)
    {
        get(modId).messageBuilder(clazz, nextDiscriminator(modId), NetworkDirection.PLAY_TO_CLIENT)
                .codec(streamCodec)
                .consumerMainThread((payload, context) ->
                        context.enqueueWork(() ->
                        {
                            ForgeV1Common.replyContext = context;
                            handler.accept(Minecraft.getInstance().player, payload);
                            ForgeV1Common.replyContext = null;
                        })).add();
    }

    @Override
    public <T extends CustomPacketPayload> void
    registerServerbound(Class<T> clazz,
                        CustomPacketPayload.Type<T> type,
                        StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
                        BiConsumer<ServerPlayer, T> handler)
    {
        get(modId).messageBuilder(clazz, nextDiscriminator(modId), NetworkDirection.PLAY_TO_SERVER)
                .codec(streamCodec)
                .consumerMainThread((payload, context) ->
                        context.enqueueWork(() ->
                        {
                            ForgeV1Common.replyContext = context;
                            handler.accept(context.getSender(), payload);
                            ForgeV1Common.replyContext = null;
                        })).add();
    }

    public static SimpleChannel get(String modId)
    {
        return channels.computeIfAbsent(modId, key ->
        {
            final var channelName = ResourceLocation.fromNamespaceAndPath(key, "network");
            var builder = ChannelBuilder.named(channelName);
            if (serverOnlyMods.contains(modId)) builder.optionalClient();
            if (clientOnlyMods.contains(modId)) builder.optionalServer();
            return builder.simpleChannel();
        });
    }

    public static SimpleChannel getOrThrow(CustomPacketPayload payload)
    {
        return getOrThrow(payload.type().id().getNamespace());
    }

    public static SimpleChannel getOrThrow(String modId)
    {
        if (!channels.containsKey(modId))
            throw new IllegalStateException("No channel for mod %s".formatted(modId));
        return channels.get(modId);
    }

    private static int nextDiscriminator(String modId)
    {
        return discriminatorCounter.compute(modId, (key, prev) -> prev != null ? prev + 1 : 0);
    }
}
