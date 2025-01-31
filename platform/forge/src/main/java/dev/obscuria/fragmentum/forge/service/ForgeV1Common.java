package dev.obscuria.fragmentum.forge.service;

import com.mojang.serialization.MapCodec;
import dev.obscuria.fragmentum.api.v1.common.IPayloadRegistrar;
import dev.obscuria.fragmentum.api.v1.common.IRegistrar;
import dev.obscuria.fragmentum.api.v1.common.ModBridge;
import dev.obscuria.fragmentum.api.v1.common.V1Common;
import dev.obscuria.fragmentum.api.v1.common.easing.CubicCurve;
import dev.obscuria.fragmentum.api.v1.common.event.Event;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal0;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal1;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal2;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal3;
import dev.obscuria.fragmentum.api.v1.common.text.TextWrapper;
import dev.obscuria.fragmentum.core.v1.common.BaseModBridge;
import dev.obscuria.fragmentum.core.v1.common.easing.BaseCubicCurve;
import dev.obscuria.fragmentum.core.v1.common.event.BaseEvent;
import dev.obscuria.fragmentum.core.v1.common.signal.BaseSignal0;
import dev.obscuria.fragmentum.core.v1.common.signal.BaseSignal1;
import dev.obscuria.fragmentum.core.v1.common.signal.BaseSignal2;
import dev.obscuria.fragmentum.core.v1.common.signal.BaseSignal3;
import dev.obscuria.fragmentum.core.v1.common.text.BaseTextWrapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Supplier;

@ApiStatus.Internal
public final class ForgeV1Common implements V1Common
{
    static @Nullable CustomPayloadEvent.Context replyContext;

    @Override
    public IRegistrar registrar(String modId)
    {
        return new ForgeRegistrar(modId);
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
    public <T extends CustomPacketPayload> void sendTo(ServerPlayer player, T payload)
    {
        ForgePayloadRegistrar.getOrThrow(payload).send(payload, PacketDistributor.PLAYER.with(player));
    }

    @Override
    public <T extends CustomPacketPayload> void sendToTracking(ServerLevel level, BlockPos pos, T payload)
    {
        ForgePayloadRegistrar.getOrThrow(payload).send(payload, PacketDistributor.TRACKING_CHUNK.with(level.getChunkAt(pos)));
    }

    @Override
    public <T extends CustomPacketPayload> void sendToTracking(Entity entity, T payload)
    {
        ForgePayloadRegistrar.getOrThrow(payload).send(payload, PacketDistributor.TRACKING_ENTITY.with(entity));
    }

    @Override
    public <T extends CustomPacketPayload> void sendToAll(MinecraftServer server, T payload)
    {
        ForgePayloadRegistrar.getOrThrow(payload).send(payload, PacketDistributor.ALL.noArg());
    }

    @Override
    public <T extends CustomPacketPayload> void sendToServer(T payload)
    {
        ForgePayloadRegistrar.getOrThrow(payload).send(payload, PacketDistributor.SERVER.noArg());
    }

    @Override
    public <T extends BlockEntity> BlockEntityType.Builder<T> newBlockEntityType(
            BiFunction<BlockPos, BlockState, T> factory,
            Block... blocks)
    {
        return BlockEntityType.Builder.of(factory::apply, blocks);
    }

    @Override
    public <T extends ParticleOptions> ParticleType<T> newParticleType(
            boolean alwaysSpawn,
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
    public SimpleParticleType newParticleType(boolean alwaysSpawn)
    {
        return new SimpleParticleType(alwaysSpawn);
    }

    @Override
    public <T extends Sensor<?>> SensorType<T> newSensorType(Supplier<T> factory)
    {
        return new SensorType<>(factory);
    }

    @Override
    public <T> Event<T> newEvent()
    {
        return new BaseEvent<>();
    }

    @Override
    public TextWrapper newTextWrapper(String text)
    {
        return new BaseTextWrapper(text);
    }

    @Override
    public TextWrapper newTextWrapper(Component component)
    {
        return new BaseTextWrapper(component);
    }

    @Override
    public Signal0 newSignal0()
    {
        return new BaseSignal0();
    }

    @Override
    public <P1> Signal1<P1> newSignal1()
    {
        return new BaseSignal1<>();
    }

    @Override
    public <P1, P2> Signal2<P1, P2> newSignal2()
    {
        return new BaseSignal2<>();
    }

    @Override
    public <P1, P2, P3> Signal3<P1, P2, P3> newSignal3()
    {
        return new BaseSignal3<>();
    }

    @Override
    public CubicCurve newCubicCurse(int resolution)
    {
        return new BaseCubicCurve(resolution);
    }

    @Override
    public ModBridge newModBridge(String id, String displayName)
    {
        return new BaseModBridge(id, displayName);
    }
}
