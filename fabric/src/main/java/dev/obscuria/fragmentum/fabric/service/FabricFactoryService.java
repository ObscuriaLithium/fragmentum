package dev.obscuria.fragmentum.fabric.service;

import com.mojang.serialization.MapCodec;
import dev.obscuria.fragmentum.service.FactoryService;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;

import java.util.function.Supplier;

public final class FabricFactoryService implements FactoryService {

    public static final FabricFactoryService SHARED = new FabricFactoryService();

    @Override
    public <T extends ParticleOptions> ParticleType<T> newParticleType(boolean alwaysSpawn, MapCodec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {

        return new ParticleType<>(alwaysSpawn) {

            @Override public MapCodec<T> codec() {
                return codec;
            }

            @Override public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                return streamCodec;
            }
        };
    }

    @Override public SimpleParticleType newParticleType(boolean alwaysSpawn) {
        return FabricParticleTypes.simple(alwaysSpawn);
    }

    @Override public <T extends Sensor<?>> SensorType<T> newSensorType(Supplier<T> factory) {
        return new SensorType<>(factory);
    }

    private FabricFactoryService() {}
}
