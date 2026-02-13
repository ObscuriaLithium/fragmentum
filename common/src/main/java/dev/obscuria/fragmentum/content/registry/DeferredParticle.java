package dev.obscuria.fragmentum.content.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("unused")
public class DeferredParticle<T extends ParticleOptions> extends Deferred<ParticleType<T>> {

    public DeferredParticle(Holder<ParticleType<T>> holder) {
        super(holder);
    }

    public @Nullable SimpleParticleType simple() {
        if (get() instanceof SimpleParticleType simple) return simple;
        return null;
    }
}
