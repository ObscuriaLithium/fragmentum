package dev.obscuria.fragmentum.fabric.service;

import com.mojang.serialization.MapCodec;
import dev.obscuria.fragmentum.api.v1.common.IPayloadRegistrar;
import dev.obscuria.fragmentum.api.v1.common.IRegistrar;
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
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public final class FabricV1Common implements V1Common
{
    static @Nullable PacketSender replyPacketSender;

    @Override
    public IRegistrar registrar(String modId)
    {
        return new FabricRegistrar(modId);
    }

    @Override
    public IPayloadRegistrar payloadRegister(String modId)
    {
        return new FabricPayloadRegistrar(modId);
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
