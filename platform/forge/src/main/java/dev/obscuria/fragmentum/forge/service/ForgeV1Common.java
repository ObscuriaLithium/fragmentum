package dev.obscuria.fragmentum.forge.service;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.obscuria.fragmentum.api.Deferred;
import dev.obscuria.fragmentum.api.v1.common.IPayloadRegistrar;
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
import dev.obscuria.fragmentum.forge.ForgeFragmentum;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
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
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.DataPackRegistryEvent;
import net.minecraftforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class ForgeV1Common implements V1Common
{
    static @Nullable CustomPayloadEvent.Context replyContext;

    @Override
    @SuppressWarnings("unchecked")
    public <T, V extends T> Deferred<T, V> register(String modId,
                                                    Registry<T> registry,
                                                    ResourceKey<T> key,
                                                    Supplier<V> valueSupplier)
    {
        final var deferredRegister = DeferredRegister.create(registry.key(), modId);
        deferredRegister.register(ForgeFragmentum.eventBus(modId));
        final var object = deferredRegister.register(key.location().getPath(), valueSupplier);
        return Deferred.of(() -> (Holder<T>) object.getHolder().orElseThrow());
    }

    @Override
    public <T> void newDataRegistry(String modId,
                                    ResourceKey<Registry<T>> key,
                                    Codec<T> codec)
    {
        ForgeFragmentum.eventBus(modId).addListener((DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(key, codec));
    }

    @Override
    public <T> void newSyncedDataRegistry(String modId,
                                          ResourceKey<Registry<T>> key,
                                          Codec<T> codec)
    {
        ForgeFragmentum.eventBus(modId).addListener((DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(key, codec, codec));
    }

    @Override
    public <T> void newSyncedDataRegistry(String modId,
                                          ResourceKey<Registry<T>> key,
                                          Codec<T> dataCodec,
                                          Codec<T> networkCodec)
    {
        ForgeFragmentum.eventBus(modId).addListener((DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(key, dataCodec, networkCodec));
    }

    @Override
    public IPayloadRegistrar payloadRegister(String modId)
    {
        return new ForgePayloadRegistrar(modId);
    }

    @Override
    public <T extends CustomPacketPayload> void reply(T payload)
    {
        if (replyContext == null)
            throw new IllegalStateException("No context to reply to");
        ForgePayloadRegistrar.getOrThrow(payload).reply(payload, replyContext);
    }

    @Override
    public <T extends CustomPacketPayload> void sendTo(ServerPlayer player,
                                                       T payload)
    {
        ForgePayloadRegistrar.getOrThrow(payload).send(payload,
                PacketDistributor.PLAYER.with(player));
    }

    @Override
    public <T extends CustomPacketPayload> void sendToTracking(ServerLevel level,
                                                               BlockPos pos,
                                                               T payload)
    {
        ForgePayloadRegistrar.getOrThrow(payload).send(payload,
                PacketDistributor.TRACKING_CHUNK.with(level.getChunkAt(pos)));
    }

    @Override
    public <T extends CustomPacketPayload> void sendToTracking(Entity entity,
                                                               T payload)
    {
        ForgePayloadRegistrar.getOrThrow(payload).send(payload,
                PacketDistributor.TRACKING_ENTITY.with(entity));
    }

    @Override
    public <T extends CustomPacketPayload> void sendToAll(MinecraftServer server,
                                                          T payload)
    {
        ForgePayloadRegistrar.getOrThrow(payload).send(payload,
                PacketDistributor.ALL.noArg());
    }

    @Override
    public <T extends CustomPacketPayload> void sendToServer(T payload)
    {
        ForgePayloadRegistrar.getOrThrow(payload).send(payload,
                PacketDistributor.SERVER.noArg());
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
