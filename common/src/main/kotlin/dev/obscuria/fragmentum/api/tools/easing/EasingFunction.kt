package dev.obscuria.fragmentum.api.tools.easing

fun interface EasingFunction {

    fun compute(delta: Float): Float

    fun scale(scale: Float): EasingFunction =
        EasingInternal.scale(this, scale)

    fun reversed(): EasingFunction =
        EasingInternal.reversed(this)

    fun merge(other: EasingFunction, ratio: Float = 0.5f): EasingFunction =
        EasingInternal.merge(this, other, ratio)

    fun mergeOut(other: EasingFunction, ratio: Float = 0.5f): EasingFunction =
        EasingInternal.mergeOut(this, other, ratio)
}