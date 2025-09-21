@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.tools.easing

import net.minecraft.util.Mth

fun interface EasingFunction {

    fun compute(delta: Float): Float

    fun scale(scale: Float): EasingFunction = EasingFunction { progress ->
        this.compute(Mth.clamp(progress * (1f / scale), 0f, 1f))
    }

    fun reversed(): EasingFunction = EasingFunction { progress ->
        1f - this.compute(progress)
    }

    fun merge(other: EasingFunction, ratio: Float = 0.5f): EasingFunction = EasingFunction { progress ->
        if (progress <= ratio) ratio * this.scale(ratio).compute(progress)
        else ratio * this.compute(1f) + (1f - ratio) * other.scale(1f - ratio).compute(progress - ratio)
    }

    fun mergeOut(other: EasingFunction, ratio: Float = 0.5f): EasingFunction = EasingFunction { progress ->
        if (progress <= ratio) this.scale(ratio).compute(progress)
        else other.reversed().scale(1f - ratio).compute(progress - ratio)
    }
}