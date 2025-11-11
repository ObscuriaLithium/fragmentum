package dev.obscuria.fragmentum.util.easing;

import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unused")
public final class CubicCurve
{
    private final List<Point> controlPoints = new ArrayList<>();
    private final List<Point> points = new ArrayList<>();
    private final int resolution;
    private boolean isDirty = true;

    public CubicCurve(int resolution)
    {
        this.resolution = Mth.clamp(resolution, 0, 100);
    }

    public CubicCurve addPoint(double x, double y)
    {
        return addPoint((float) x, (float) y);
    }

    public CubicCurve addPoint(float x, float y)
    {
        controlPoints.add(new Point(x, y));
        controlPoints.sort(Comparator.comparingDouble(it -> it.x));
        isDirty = true;
        return this;
    }

    public Point samplePoint(float x)
    {
        if (isDirty) bake();
        if (points.isEmpty()) return Point.ZERO;
        final var index = Mth.clamp(x * (points.size() - 1), 0, points.size() - 1);
        return points.get((int) index);
    }

    public float sample(float x)
    {
        return samplePoint(x).y;
    }

    public EasingFunction toEasing()
    {
        return this::sample;
    }

    private void bake()
    {
        isDirty = false;
        points.clear();

        final var res = 1f / (float) resolution;
        final var segments = controlPoints.size() - 1;

        for (int i = 0; i < segments; i++)
        {
            final var point = controlPoints.get(i);
            final var nextPoint = controlPoints.get(i + 1);

            final var xDiff = nextPoint.x - point.x;
            final var yDiff = nextPoint.y - point.y;

            var step = 0f;
            while (step <= 1f)
            {
                float x = point.x + step * xDiff;
                float y = point.y + step * step * (3 * yDiff - 2 * step * yDiff);
                points.add(new Point(x, y));
                step += res;
            }
        }
    }

    public record Point(float x, float y)
    {
        public static final Point ZERO = new Point(0, 0);
    }
}
