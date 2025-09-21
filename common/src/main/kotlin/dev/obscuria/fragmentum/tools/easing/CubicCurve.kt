@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType", "MemberVisibilityCanBePrivate")

package dev.obscuria.fragmentum.tools.easing

import net.minecraft.util.Mth
import kotlin.math.max
import kotlin.math.min

class CubicCurve(resolution: Int) {

    private val rawPoints: MutableList<Point> = mutableListOf()
    private val cachedPoints: MutableList<Point> = mutableListOf()
    private val resolution = min(max(resolution.toDouble(), 0.0), 100.0).toInt()
    private var dirty = true

    fun addPoint(x: Double, y: Double): CubicCurve {
        return this.addPoint(x.toFloat(), y.toFloat())
    }

    fun addPoint(x: Float, y: Float): CubicCurve {
        rawPoints.add(Point(Mth.clamp(x, 0f, 1f), Mth.clamp(x, 0f, 1f)))
        rawPoints.sortWith(Comparator.comparingDouble { it.x.toDouble() })
        this.dirty = true
        return this
    }

    fun samplePoint(x: Float): Point {
        if (this.dirty) this.bake()
        if (cachedPoints.isEmpty()) return Point.ZERO
        val index = Mth.clamp(x * (cachedPoints.size - 1), 0f, (cachedPoints.size - 1).toFloat()).toInt()
        return cachedPoints[index]
    }

    fun sample(x: Float): Float {
        return samplePoint(x).y
    }

    fun asEasing(): EasingFunction {
        return EasingFunction { progress -> sample(progress) }
    }

    private fun bake() {
        this.dirty = false
        cachedPoints.clear()

        val res = 1f / resolution.toFloat()
        val segments = rawPoints.size - 1

        for (i in 0..<segments) {
            val point: Point = rawPoints[i]
            val nextPoint: Point = rawPoints[i + 1]
            val xDiff: Float = nextPoint.x - point.x
            val yDiff: Float = nextPoint.y - point.y

            var step = 0f
            while (step <= 1) {
                val x: Float = point.x + step * xDiff
                val y: Float = point.y + step * step * (3 * yDiff - 2 * step * yDiff)
                cachedPoints.add(Point(x, y))
                step += res
            }
        }
    }

    data class Point(val x: Float, val y: Float) {

        companion object {

            val ZERO: Point = Point(0f, 0f)
        }
    }
}