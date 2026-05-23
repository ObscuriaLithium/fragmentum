package dev.obscuria.fragmentum.v2.core;

import com.mojang.serialization.MapCodec;
import dev.obscuria.fragmentum.Fragmentum;
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
public final class _FragmentumFactory {

    public static <T extends ParticleOptions> ParticleType<T> newParticleType(boolean alwaysSpawn, MapCodec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
        return Fragmentum.SERVICES.factory().newParticleType(alwaysSpawn, codec, streamCodec);
    }

    public static SimpleParticleType newParticleType(boolean alwaysSpawn) {
        return Fragmentum.SERVICES.factory().newParticleType(alwaysSpawn);
    }

    public static <T extends Sensor<?>> SensorType<T> newSensorType(Supplier<T> factory) {
        return Fragmentum.SERVICES.factory().newSensorType(factory);
    }
}
