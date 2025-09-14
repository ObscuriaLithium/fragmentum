package dev.obscuria.fragmentum.api.tools.easing

import dev.obscuria.fragmentum.api.Fragmentum

interface CubicCurve {

    fun addPoint(x: Float, y: Float): CubicCurve

    fun addPoint(x: Double, y: Double): CubicCurve = this.addPoint(x.toFloat(), y.toFloat())

    fun samplePoint(x: Float): Point

    fun sample(x: Float): Float

    fun asEasing(): EasingFunction

    data class Point(val x: Float, val y: Float) {
        companion object {
            val ZERO: Point = Point(0f, 0f)
        }
    }

    companion object {

        fun create(resolution: Int): CubicCurve {
            return Fragmentum.SERVICES.newCubicCurse(resolution)
        }
    }
}