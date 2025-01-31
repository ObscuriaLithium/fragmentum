package dev.obscuria.fragmentum.api.v1.common;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * A factory interface for creating various types of game components in the Fragmentum framework.
 */
public interface FragmentumFactory
{
    /**
     * Creates a new {@link BlockEntityType.Builder} for defining block entity types.
     *
     * @param <T>     The type of the block entity.
     * @param factory A {@link BiFunction} responsible for creating block entities from their {@link BlockPos} and {@link BlockState}.
     * @param blocks  A collection of {@link Block}s that the block entity applies to.
     * @return A {@link BlockEntityType.Builder} instance to further configure and build the block entity type.
     */
    static <T extends BlockEntity> BlockEntityType.Builder<T> newBlockEntityType(
            BiFunction<BlockPos, BlockState, T> factory,
            Block... blocks)
    {
        return V1Common.INSTANCE.newBlockEntityType(factory, blocks);
    }

    /**
     * Creates a new {@link ParticleType} with support for complex serialization and networking.
     *
     * @param <T>         The type of the custom {@link ParticleOptions}.
     * @param alwaysSpawn Determines whether the particle is allowed to spawn outside the client's render distance.
     * @param codec       A {@link MapCodec} for serializing and deserializing the particle data.
     * @param streamCodec A {@link StreamCodec} for encoding and decoding particle data for networking.
     * @return A custom {@link ParticleType} for the defined particle options.
     */
    static <T extends ParticleOptions> ParticleType<T> newParticleType(
            boolean alwaysSpawn,
            MapCodec<T> codec,
            StreamCodec<RegistryFriendlyByteBuf, T> streamCodec)
    {
        return V1Common.INSTANCE.newParticleType(alwaysSpawn, codec, streamCodec);
    }

    /**
     * Creates a new simple particle of type {@link SimpleParticleType}. This particle type does not require
     * custom serialization or additional data beyond the default particle behavior.
     *
     * @param alwaysSpawn Determines whether the particle is allowed to spawn outside the client's render distance.
     * @return A {@link SimpleParticleType} instance for simple particle behavior.
     */
    static SimpleParticleType newParticleType(boolean alwaysSpawn)
    {
        return V1Common.INSTANCE.newParticleType(alwaysSpawn);
    }

    /**
     * Creates a new {@link SensorType} for use in the Minecraft AI system.
     *
     * @param <T>     The specific type of the {@link Sensor}.
     * @param factory A {@link Supplier} that creates instances of the custom sensor.
     * @return A custom {@link SensorType} for the given sensor implementation.
     */
    static <T extends Sensor<?>> SensorType<T> newSensorType(Supplier<T> factory)
    {
        return V1Common.INSTANCE.newSensorType(factory);
    }
}