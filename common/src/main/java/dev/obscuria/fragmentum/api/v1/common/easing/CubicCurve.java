package dev.obscuria.fragmentum.api.v1.common.easing;

import dev.obscuria.fragmentum.api.v1.common.V1Common;

/**
 * Represents a cubic curve with defined control points that can be sampled
 * for interpolation or converted into an easing function.
 */
@SuppressWarnings("unused")
public interface CubicCurve
{
    /**
     * Creates a new cubic curve instance with the specified resolution.
     * <p>
     * The resolution determines how finely the curve is sampled. Higher resolutions
     * produce smoother curves but may impact performance.
     *
     * @param resolution The resolution of the curve.
     * @return A new instance of {@code CubicCurve}.
     */
    static CubicCurve create(int resolution)
    {
        return V1Common.INSTANCE.newCubicCurse(resolution);
    }

    /**
     * Adds a control point to the curve.
     * <p>
     * Control points define the shape of the cubic curve. Points are added sequentially
     * and influence the curve's interpolation behavior.
     *
     * @param x The x-coordinate of the control point.
     * @param y The y-coordinate of the control point.
     * @return The current instance of {@code CubicCurve} for method chaining.
     */
    CubicCurve addPoint(float x, float y);

    /**
     * Adds a control point to the curve using double-precision coordinates.
     * <p>
     * This method casts the double values to floats internally and is equivalent
     * to {@link #addPoint(float, float)}.
     *
     * @param x The x-coordinate of the control point.
     * @param y The y-coordinate of the control point.
     * @return The current instance of {@code CubicCurve} for method chaining.
     */
    default CubicCurve addPoint(double x, double y)
    {
        return this.addPoint((float) x, (float) y);
    }

    /**
     * Samples a point on the curve for a given x-value.
     * <p>
     * Retrieves the interpolated y-coordinate for the specified x-coordinate by
     * evaluating the curve at that point.
     *
     * @param x The x-coordinate to sample.
     * @return A {@link Point} representing the sampled coordinates.
     */
    Point samplePoint(float x);

    /**
     * Samples the curve for a given x-value.
     * <p>
     * This method retrieves only the y-coordinate of the curve at the specified x-coordinate
     * without returning the full {@link Point}.
     *
     * @param x The x-coordinate to sample.
     * @return The interpolated y-coordinate at the specified x-value.
     */
    double sample(float x);

    /**
     * Converts the cubic curve into an easing function.
     * <p>
     * The easing function can be used to provide smooth transitions (e.g., for animations),
     * reflecting the behavior of the cubic curve.
     *
     * @return An {@link EasingFunction} representing the cubic curve.
     */
    EasingFunction asEasing();

    /**
     * Represents a point on the cubic curve.
     * <p>
     * The {@link Point} record encapsulates the x and y coordinates of a point and may
     * be used for adding control points or sampling the curve.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    record Point(float x, float y)
    {
        public static final Point ZERO = new Point(0, 0);
    }
}