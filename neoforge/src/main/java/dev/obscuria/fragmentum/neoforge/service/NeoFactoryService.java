package dev.obscuria.fragmentum.neoforge.service;

import com.mojang.serialization.MapCodec;
import dev.obscuria.fragmentum.service.FactoryService;
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

public final class NeoFactoryService implements FactoryService {

    public static final NeoFactoryService INSTANCE = new NeoFactoryService();

    private NeoFactoryService() {}

    @Override
    public <T extends BlockEntity> BlockEntityType.Builder<T> newBlockEntityType(BiFunction<BlockPos, BlockState, T> factory, Block... blocks) {
        return BlockEntityType.Builder.of(factory::apply, blocks);
    }

    @Override
    @SuppressWarnings("ALL")
    public <T extends ParticleOptions> ParticleType<T> newParticleType(boolean alwaysSpawn, MapCodec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
        return new ParticleType<>(alwaysSpawn) {
            @Override
            public MapCodec<T> codec() {
                return codec;
            }

            @Override
            public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                return streamCodec;
            }
        };
    }

    @Override
    public SimpleParticleType newParticleType(boolean alwaysSpawn) {
        return new SimpleParticleType(alwaysSpawn);
    }

    @Override
    public <T extends Sensor<?>> SensorType<T> newSensorType(Supplier<T> factory) {
        return new SensorType<>(factory);
    }
}
