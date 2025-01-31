package dev.obscuria.fragmentum.api;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import org.apache.logging.log4j.util.Lazy;

/**
 * A specialized version of {@link Deferred} for handling deferred access to {@link ParticleType} instances in Minecraft.
 * This class provides functionality for deferred retrieval and access of particle types and their simple forms.
 *
 * @param <T> The specific type of {@link ParticleOptions} associated with this {@code DeferredParticle}.
 */
public class DeferredParticle<T extends ParticleOptions> extends Deferred<ParticleType<?>, ParticleType<T>>
{
    /**
     * Constructs a new {@code DeferredParticle} with a {@link Lazy} holder of a {@link ParticleType}.
     *
     * @param lazyHolder A {@link Lazy} holder that provides deferred access to a {@link Holder} of {@link ParticleType}.
     */
    public DeferredParticle(Lazy<Holder<ParticleType<?>>> lazyHolder)
    {
        super(lazyHolder);
    }

    /**
     * Retrieves the wrapped particle type as a {@link SimpleParticleType}, if the particle type supports it.
     *
     * @return The {@link SimpleParticleType} represented by this particle type.
     * @throws ClassCastException If the particle type cannot be cast to {@link SimpleParticleType}.
     */
    public SimpleParticleType simple()
    {
        return (SimpleParticleType) value();
    }
}