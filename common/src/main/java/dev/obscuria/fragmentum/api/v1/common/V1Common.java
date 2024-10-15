package dev.obscuria.fragmentum.api.v1.common;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.obscuria.fragmentum.api.Deferred;
import dev.obscuria.fragmentum.api.v1.common.easing.CubicCurve;
import dev.obscuria.fragmentum.api.v1.common.event.Event;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal0;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal1;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal2;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal3;
import dev.obscuria.fragmentum.api.v1.common.text.TextWrapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.ApiStatus;

import java.util.ServiceLoader;
import java.util.function.BiFunction;
import java.util.function.Supplier;

@ApiStatus.Internal
public interface V1Common
{
    V1Common INSTANCE = ServiceLoader.load(V1Common.class).findFirst().orElseThrow();

    // REGISTRIES

    <T, V extends T> Deferred<T, V> register(String modId,
                                             Registry<T> registry,
                                             ResourceKey<T> key,
                                             Supplier<V> valueSupplier);

    <T> void newDataRegistry(String modId,
                             ResourceKey<Registry<T>> key,
                             Codec<T> codec);

    <T> void newSyncedDataRegistry(String modId,
                                   ResourceKey<Registry<T>> key,
                                   Codec<T> codec);


    <T> void newSyncedDataRegistry(String modId,
                                   ResourceKey<Registry<T>> key,
                                   Codec<T> dataCodec,
                                   Codec<T> networkCodec);

    // NETWORKING

    IPayloadRegistrar payloadRegister(String modId);

    <T extends CustomPacketPayload> void reply(T payload);

    <T extends CustomPacketPayload> void sendTo(ServerPlayer player, T payload);

    <T extends CustomPacketPayload> void sendToTracking(ServerLevel world, BlockPos pos, T payload);

    <T extends CustomPacketPayload> void sendToTracking(Entity entity, T payload);

    <T extends CustomPacketPayload> void sendToAll(MinecraftServer server, T payload);

    <T extends CustomPacketPayload> void sendToServer(T payload);

    // FACTORIES

    <T extends BlockEntity> BlockEntityType.Builder<T>
    createBlockEntityType(BiFunction<BlockPos, BlockState, T> factory,
                          Block... blocks);

    <T extends ParticleOptions> ParticleType<T>
    createParticleType(boolean alwaysSpawn,
                       MapCodec<T> codec,
                       StreamCodec<RegistryFriendlyByteBuf, T> streamCodec);

    // COMMON

    <T> Event<T> newEvent();

    TextWrapper newTextWrapper(String text);

    TextWrapper newTextWrapper(Component component);

    Signal0 newSignal0();

    <P1> Signal1<P1> newSignal1();

    <P1, P2> Signal2<P1, P2> newSignal2();

    <P1, P2, P3> Signal3<P1, P2, P3> newSignal3();

    CubicCurve newCubicCurse(int resolution);
}
