package dev.obscuria.fragmentum.neoforge.service;

import com.mojang.serialization.MapCodec;
import dev.obscuria.fragmentum.api.v1.common.IPayloadRegistrar;
import dev.obscuria.fragmentum.api.v1.common.IRegistrar;
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
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public final class NeoV1Common implements V1Common
{
    static @Nullable Consumer<CustomPacketPayload> replyConsumer;

    @Override
    public IRegistrar registrar(String modId)
    {
        return new NeoRegistrar(modId);
    }

    @Override
    public IPayloadRegistrar payloadRegister(String modId)
    {
        return new NeoPayloadRegistrar(modId, new AtomicBoolean(false));
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
