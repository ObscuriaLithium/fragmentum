@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.tools.easing

fun interface EasingFunction {

    fun compute(delta: Float): Float

    fun scale(scale: Float): EasingFunction = error("API stub")

    fun reversed(): EasingFunction = error("API stub")

    fun merge(other: EasingFunction, ratio: Float = 0.5f): EasingFunction = error("API stub")

    fun mergeOut(other: EasingFunction, ratio: Float = 0.5f): EasingFunction = error("API stub")
}