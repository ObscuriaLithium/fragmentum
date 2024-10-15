package dev.obscuria.fragmentum.api.v1.common.easing;

import dev.obscuria.fragmentum.api.v1.common.V1Common;

@SuppressWarnings("unused")
public interface CubicCurve
{
    static CubicCurve create(int resolution)
    {
        return V1Common.INSTANCE.newCubicCurse(resolution);
    }

    CubicCurve addPoint(float x, float y);

    default CubicCurve addPoint(double x, double y)
    {
        return this.addPoint((float) x, (float) y);
    }

    Point samplePoint(float x);

    double sample(float x);

    EasingFunction asEasing();

    record Point(float x, float y)
    {
        public static final Point ZERO = new Point(0, 0);
    }
}