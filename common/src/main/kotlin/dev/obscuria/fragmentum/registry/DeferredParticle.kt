@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.registry

import net.minecraft.core.Holder
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType

class DeferredParticle<T : ParticleOptions>(
    supplier: () -> Holder<ParticleType<*>>
) : Deferred<ParticleType<*>, ParticleType<T>>(supplier) {

    fun simple(): SimpleParticleType? = value as? SimpleParticleType
}