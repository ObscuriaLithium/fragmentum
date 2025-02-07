package dev.obscuria.fragmentum.api.registry

import net.minecraft.core.Holder
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import java.util.function.Supplier

class DeferredParticle<T : ParticleOptions>(supplier: Supplier<Holder<ParticleType<*>>>)
    : Deferred<ParticleType<*>, ParticleType<T>>(supplier)
{
    fun simple(): SimpleParticleType = value() as SimpleParticleType
}