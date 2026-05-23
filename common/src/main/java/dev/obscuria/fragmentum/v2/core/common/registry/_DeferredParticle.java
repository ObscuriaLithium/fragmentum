package dev.obscuria.fragmentum.v2.core.common.registry;

import dev.obscuria.fragmentum.v2.api.common.registry.DeferredParticle;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class _DeferredParticle<T extends ParticleOptions> extends _Deferred<ParticleType<T>> implements DeferredParticle<T> {

    public _DeferredParticle(Holder<ParticleType<T>> holder) {
        super(holder);
    }

    @Override
    public @Nullable SimpleParticleType simple() {
        if (get() instanceof SimpleParticleType simple) return simple;
        return null;
    }
}
