package dev.obscuria.fragmentum.content.util.easing;

import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("unused")
public final class CubicCurve
{
    public CubicCurve(int resolution) {}

    public CubicCurve addPoint(float x, float y)
    {
        throw new NotImplementedException();
    }

    public CubicCurve addPoint(double x, double y)
    {
        throw new NotImplementedException();
    }

    public Point samplePoint(float x)
    {
        throw new NotImplementedException();
    }

    public float sample(float x)
    {
        throw new NotImplementedException();
    }

    public EasingFunction toEasing()
    {
        throw new NotImplementedException();
    }

    public record Point(float x, float y)
    {
        public static final Point ZERO = new Point(0, 0);
    }
}
