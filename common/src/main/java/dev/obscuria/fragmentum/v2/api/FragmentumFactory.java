package dev.obscuria.fragmentum.v2.api;

import com.mojang.serialization.MapCodec;
import dev.obscuria.fragmentum.v2.core._FragmentumFactory;
import lombok.experimental.UtilityClass;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;

import java.util.function.Supplier;

@UtilityClass
@SuppressWarnings("unused")
public final class FragmentumFactory {

    public static <T extends ParticleOptions> ParticleType<T> newParticleType(boolean alwaysSpawn, MapCodec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
        return _FragmentumFactory.newParticleType(alwaysSpawn, codec, streamCodec);
    }

    public static SimpleParticleType newParticleType(boolean alwaysSpawn) {
        return _FragmentumFactory.newParticleType(alwaysSpawn);
    }

    public static <T extends Sensor<?>> SensorType<T> newSensorType(Supplier<T> factory) {
        return _FragmentumFactory.newSensorType(factory);
    }
}
