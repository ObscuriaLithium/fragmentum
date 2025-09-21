@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.tools.easing

class CubicCurve(val resolution: Int) {

    fun addPoint(x: Float, y: Float): CubicCurve = error("API stub")

    fun addPoint(x: Double, y: Double): CubicCurve = error("API stub")

    fun samplePoint(x: Float): Point = error("API stub")

    fun sample(x: Float): Float = error("API stub")

    fun asEasing(): EasingFunction = error("API stub")

    data class Point(val x: Float, val y: Float) {

        companion object {

            val ZERO: Point = Point(0f, 0f)
        }
    }
}