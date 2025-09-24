package dev.obscuria.fragmentum;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.BiFunction;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface FragmentumFactory
{
    static <T extends BlockEntity> BlockEntityType.Builder<T> newBlockEntityType(BiFunction<BlockPos, BlockState, T> factory, Block... blocks)
    {
        throw new NotImplementedException();
    }

    @SuppressWarnings("deprecation")
    static <T extends ParticleOptions> ParticleType<T> newParticleType(boolean alwaysSpawn, Codec<T> codec, ParticleOptions.Deserializer<T> deserializer)
    {
        throw new NotImplementedException();
    }

    static SimpleParticleType newParticleType(boolean alwaysSpawn)
    {
        throw new NotImplementedException();
    }

    static <T extends Sensor<?>> SensorType<T> newSensorType(Supplier<T> factory)
    {
        throw new NotImplementedException();
    }
}
