package dev.obscuria.fragmentum.v2.api.common.registry;

import dev.obscuria.fragmentum.v2.core.common.registry._DeferredParticle;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("unused")
public interface DeferredParticle<T extends ParticleOptions> extends Deferred<ParticleType<T>> {

    static <T extends ParticleOptions> DeferredParticle<T> create(Holder<ParticleType<T>> holder) {
       return new _DeferredParticle<>(holder);
    }

    @Nullable SimpleParticleType simple();
}
