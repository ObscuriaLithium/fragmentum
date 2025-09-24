package dev.obscuria.fragmentum.forge.service;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.service.FactoryService;
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

import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class ForgeFactoryService implements FactoryService
{
    public static final ForgeFactoryService INSTANCE = new ForgeFactoryService();

    private ForgeFactoryService() {}

    @Override
    public <T extends BlockEntity> BlockEntityType.Builder<T> newBlockEntityType(BiFunction<BlockPos, BlockState, T> factory, Block... blocks)
    {
        return BlockEntityType.Builder.of(factory::apply, blocks);
    }

    @Override
    @SuppressWarnings("ALL")
    public <T extends ParticleOptions> ParticleType<T> newParticleType(boolean alwaysSpawn, Codec<T> codec, ParticleOptions.Deserializer<T> deserializer)
    {
        return new ParticleType<>(alwaysSpawn, deserializer)
        {
            @Override
            public Codec<T> codec()
            {
                return codec;
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
}
