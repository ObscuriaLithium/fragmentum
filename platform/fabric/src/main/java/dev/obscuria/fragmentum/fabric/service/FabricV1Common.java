package dev.obscuria.fragmentum.fabric.service;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.obscuria.fragmentum.api.Deferred;
import dev.obscuria.fragmentum.api.v1.common.V1Common;
import dev.obscuria.fragmentum.api.v1.common.easing.CubicCurve;
import dev.obscuria.fragmentum.api.v1.common.event.Event;
import dev.obscuria.fragmentum.api.v1.common.signal.*;
import dev.obscuria.fragmentum.api.v1.common.text.TextWrapper;
import dev.obscuria.fragmentum.core.v1.common.easing.CubicCurveImpl;
import dev.obscuria.fragmentum.core.v1.common.event.EventImpl;
import dev.obscuria.fragmentum.core.v1.common.signal.Signal0Impl;
import dev.obscuria.fragmentum.core.v1.common.signal.Signal1Impl;
import dev.obscuria.fragmentum.core.v1.common.signal.Signal2Impl;
import dev.obscuria.fragmentum.core.v1.common.signal.Signal3Impl;
import dev.obscuria.fragmentum.core.v1.common.text.TextWrapperImpl;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class FabricV1Common implements V1Common
{
    private static @Nullable PacketSender replyPacketSender;

    @Override
    public <T, V extends T> Deferred<T, V> register(String modId,
                                                    Registry<T> registry,
                                                    ResourceKey<T> key,
                                                    Supplier<V> valueSupplier)
    {
        return new Deferred<>(Registry.registerForHolder(registry, key, valueSupplier.get()));
    }

    @Override
    public <T> void newDataRegistry(String modId,
                                    ResourceKey<Registry<T>> key,
                                    Codec<T> codec)
    {
        DynamicRegistries.register(key, codec);
    }

    @Override
    public <T> void newSyncedDataRegistry(String modId,
                                          ResourceKey<Registry<T>> key,
                                          Codec<T> codec)
    {
        DynamicRegistries.registerSynced(key, codec);
    }

    @Override
    public <T> void newSyncedDataRegistry(String modId,
                                          ResourceKey<Registry<T>> key,
                                          Codec<T> dataCodec,
                                          Codec<T> networkCodec)
    {
        DynamicRegistries.registerSynced(key, dataCodec, networkCodec);
    }

    @Override
    public <T extends CustomPacketPayload> void
    registerClientbound(String modId,
                        Class<T> clazz,
                        CustomPacketPayload.Type<T> type,
                        StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
                        BiConsumer<Player, T> handler)
    {
        PayloadTypeRegistry.playS2C().register(type, streamCodec);
        ClientPlayNetworking.registerGlobalReceiver(type, (payload, context) -> {
            final var client = context.client();
            if (client == null) return;
            client.execute(() -> {
                replyPacketSender = context.responseSender();
                handler.accept(context.player(), payload);
                replyPacketSender = null;
            });
        });
    }

    @Override
    public <T extends CustomPacketPayload> void
    registerServerbound(String modId,
                        Class<T> clazz,
                        CustomPacketPayload.Type<T> type,
                        StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
                        BiConsumer<ServerPlayer, T> handler)
    {
        PayloadTypeRegistry.playC2S().register(type, streamCodec);
        ServerPlayNetworking.registerGlobalReceiver(type, (payload, context) -> {
            final var server = context.player().getServer();
            if (server == null) return;
            server.execute(() -> {
                replyPacketSender = context.responseSender();
                handler.accept(context.player(), payload);
                replyPacketSender = null;
            });
        });
    }

    @Override
    public <T extends CustomPacketPayload> void reply(T payload)
    {
        if (replyPacketSender == null)
            throw new IllegalStateException("No context to reply to");
        replyPacketSender.sendPacket(payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendTo(ServerPlayer player,
                                                       T payload)
    {
        ServerPlayNetworking.send(player, payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToTracking(ServerLevel world,
                                                               BlockPos pos,
                                                               T payload)
    {
        for (ServerPlayer player : PlayerLookup.tracking(world, pos))
            ServerPlayNetworking.send(player, payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToTracking(Entity entity,
                                                               T payload)
    {
        for (ServerPlayer player : PlayerLookup.tracking(entity))
            ServerPlayNetworking.send(player, payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToAll(MinecraftServer server,
                                                          T payload)
    {
        for (ServerPlayer player : PlayerLookup.all(server))
            ServerPlayNetworking.send(player, payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToServer(T payload)
    {
        ClientPlayNetworking.send(payload);
    }

    @Override
    public <T extends BlockEntity> BlockEntityType.Builder<T>
    createBlockEntityType(BiFunction<BlockPos, BlockState, T> factory,
                          Block... blocks)
    {
        return BlockEntityType.Builder.of(factory::apply, blocks);
    }

    @Override
    public <T extends ParticleOptions> ParticleType<T>
    createParticleType(boolean alwaysSpawn,
                       MapCodec<T> codec,
                       StreamCodec<RegistryFriendlyByteBuf, T> streamCodec)
    {
        return FabricParticleTypes.complex(alwaysSpawn, codec, streamCodec);
    }

    @Override
    public <T> Event<T> newEvent()
    {
        return new EventImpl<>();
    }

    @Override
    public TextWrapper newTextWrapper(String text)
    {
        return new TextWrapperImpl(text);
    }

    @Override
    public TextWrapper newTextWrapper(Component component)
    {
        return new TextWrapperImpl(component);
    }

    @Override
    public Signal0 newSignal0()
    {
        return new Signal0Impl();
    }

    @Override
    public <P1> Signal1<P1> newSignal1()
    {
        return new Signal1Impl<>();
    }

    @Override
    public <P1, P2> Signal2<P1, P2> newSignal2()
    {
        return new Signal2Impl<>();
    }

    @Override
    public <P1, P2, P3> Signal3<P1, P2, P3> newSignal3()
    {
        return new Signal3Impl<>();
    }

    @Override
    public CubicCurve newCubicCurse(int resolution)
    {
        return new CubicCurveImpl(resolution);
    }
}
