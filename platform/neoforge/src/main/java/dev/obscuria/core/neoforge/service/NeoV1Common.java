package dev.obscuria.fragmentum.neoforge.service;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.obscuria.fragmentum.api.Deferred;
import dev.obscuria.fragmentum.api.v1.common.V1Common;
import dev.obscuria.fragmentum.api.v1.common.easing.CubicCurve;
import dev.obscuria.fragmentum.api.v1.common.event.Event;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal0;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal1;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal2;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal3;
import dev.obscuria.fragmentum.api.v1.common.text.TextWrapper;
import dev.obscuria.fragmentum.core.v1.common.easing.CubicCurveImpl;
import dev.obscuria.fragmentum.core.v1.common.event.EventImpl;
import dev.obscuria.fragmentum.core.v1.common.signal.Signal0Impl;
import dev.obscuria.fragmentum.core.v1.common.signal.Signal1Impl;
import dev.obscuria.fragmentum.core.v1.common.signal.Signal2Impl;
import dev.obscuria.fragmentum.core.v1.common.signal.Signal3Impl;
import dev.obscuria.fragmentum.core.v1.common.text.TextWrapperImpl;
import dev.obscuria.fragmentum.neoforge.NeoObscureAPI;
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
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class NeoV1Common implements V1Common
{
    private static @Nullable Consumer<CustomPacketPayload> replyConsumer;

    @Override
    public <T, V extends T> Deferred<T, V> register(String modId,
                                                    Registry<T> registry,
                                                    ResourceKey<T> key,
                                                    Supplier<V> valueSupplier)
    {
        final var deferredRegister = DeferredRegister.create(registry, modId);
        deferredRegister.register(NeoObscureAPI.eventBus(modId));
        return new Deferred<>(deferredRegister.register(key.location().getPath(), valueSupplier));
    }

    @Override
    public <T> void newDataRegistry(String modId,
                                    ResourceKey<Registry<T>> key,
                                    Codec<T> codec)
    {
        NeoObscureAPI.eventBus(modId).addListener((DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(key, codec));
    }

    @Override
    public <T> void newSyncedDataRegistry(String modId,
                                          ResourceKey<Registry<T>> key,
                                          Codec<T> codec)
    {
        NeoObscureAPI.eventBus(modId).addListener((DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(key, codec, codec));
    }

    @Override
    public <T> void newSyncedDataRegistry(String modId,
                                          ResourceKey<Registry<T>> key,
                                          Codec<T> dataCodec,
                                          Codec<T> networkCodec)
    {
        NeoObscureAPI.eventBus(modId).addListener((DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(key, dataCodec, networkCodec));
    }

    @Override
    public <T extends CustomPacketPayload> void
    registerClientbound(String modId,
                        Class<T> clazz,
                        CustomPacketPayload.Type<T> type,
                        StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
                        BiConsumer<Player, T> handler)
    {
        NeoObscureAPI.eventBus(modId).addListener((RegisterPayloadHandlersEvent event) -> {
            final var registrar = event.registrar("1");
            registrar.playToClient(type, streamCodec, (payload, context) -> {
                context.enqueueWork(() -> {
                    replyConsumer = context::reply;
                    handler.accept(context.player(), payload);
                    replyConsumer = null;
                });
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
        NeoObscureAPI.eventBus(modId).addListener((RegisterPayloadHandlersEvent event) -> {
            final var registrar = event.registrar("1");
            registrar.playToServer(type, streamCodec, (payload, context) -> {
                context.enqueueWork(() -> {
                    replyConsumer = context::reply;
                    handler.accept((ServerPlayer) context.player(), payload);
                    replyConsumer = null;
                });
            });
        });
    }

    @Override
    public <T extends CustomPacketPayload> void reply(T payload)
    {
        if (replyConsumer == null)
            throw new IllegalStateException("No context to reply to");
        replyConsumer.accept(payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendTo(ServerPlayer player,
                                                       T payload)
    {
        PacketDistributor.sendToPlayer(player, payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToTracking(ServerLevel level,
                                                               BlockPos pos,
                                                               T payload)
    {
        PacketDistributor.sendToPlayersTrackingChunk(level, new ChunkPos(pos), payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToTracking(Entity entity,
                                                               T payload)
    {
        PacketDistributor.sendToPlayersTrackingEntity(entity, payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToAll(MinecraftServer server,
                                                          T payload)
    {
        PacketDistributor.sendToAllPlayers(payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToServer(T payload)
    {
        PacketDistributor.sendToServer(payload);
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
        return new ParticleType<>(alwaysSpawn)
        {
            @Override
            public MapCodec<T> codec()
            {
                return codec;
            }

            @Override
            public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec()
            {
                return streamCodec;
            }
        };
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
