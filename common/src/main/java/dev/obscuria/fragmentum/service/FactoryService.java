package dev.obscuria.fragmentum.service;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

@ApiStatus.Internal
public interface FactoryService {

    <T extends ParticleOptions> ParticleType<T> newParticleType(boolean alwaysSpawn, MapCodec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec);

    SimpleParticleType newParticleType(boolean alwaysSpawn);

    <T extends Sensor<?>> SensorType<T> newSensorType(Supplier<T> factory);
}
