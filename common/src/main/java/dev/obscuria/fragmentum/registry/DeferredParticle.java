package dev.obscuria.fragmentum.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class DeferredParticle<T extends ParticleOptions> extends Deferred<ParticleType<?>, ParticleType<T>>
{
    public DeferredParticle(Supplier<Holder<ParticleType<?>>> supplier)
    {
        super(supplier);
    }

    public @Nullable SimpleParticleType simple()
    {
        if (get() instanceof SimpleParticleType simple) return simple;
        return null;
    }
}
