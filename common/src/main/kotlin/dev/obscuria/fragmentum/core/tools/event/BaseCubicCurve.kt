package dev.obscuria.fragmentum.core.tools.event

import com.google.common.collect.Lists
import dev.obscuria.fragmentum.api.tools.easing.CubicCurve
import dev.obscuria.fragmentum.api.tools.easing.CubicCurve.Point
import dev.obscuria.fragmentum.api.tools.easing.EasingFunction
import net.minecraft.util.Mth
import kotlin.math.max
import kotlin.math.min

class BaseCubicCurve(resolution: Int) : CubicCurve
{
    private val rawPoints: MutableList<Point> = Lists.newArrayList<Point>()
    private val cachedPoints: MutableList<Point> = Lists.newArrayList<Point>()
    private val resolution = min(max(resolution.toDouble(), 0.0), 100.0).toInt()
    private var dirty = true

    override fun addPoint(x: Float, y: Float): BaseCubicCurve
    {
        rawPoints.add(Point(Mth.clamp(x, 0f, 1f), Mth.clamp(x, 0f, 1f)))
        rawPoints.sortWith(Comparator.comparingDouble { it.x.toDouble() })
        this.dirty = true
        return this
    }

    override fun samplePoint(x: Float): Point
    {
        if (this.dirty) this.bake()
        if (cachedPoints.isEmpty()) return Point.ZERO
        val index = Mth.clamp(x * (cachedPoints.size - 1), 0f, (cachedPoints.size - 1).toFloat()).toInt()
        return cachedPoints[index]
    }

    override fun sample(x: Float): Float
    {
        return samplePoint(x).y
    }

    override fun asEasing(): EasingFunction
    {
        return EasingFunction { progress -> sample(progress).toFloat() }
    }

    private fun bake()
    {
        this.dirty = false
        cachedPoints.clear()

        val res = 1f / resolution.toFloat()
        val segments = rawPoints.size - 1

        for (i in 0..<segments)
        {
            val point: Point = rawPoints[i]
            val nextPoint: Point = rawPoints[i + 1]
            val xDiff: Float = nextPoint.x - point.x
            val yDiff: Float = nextPoint.y - point.y

            var step = 0f
            while (step <= 1)
            {
                val x: Float = point.x + step * xDiff
                val y: Float = point.y + step * step * (3 * yDiff - 2 * step * yDiff)
                cachedPoints.add(Point(x, y))
                step += res
            }
        }
    }
}