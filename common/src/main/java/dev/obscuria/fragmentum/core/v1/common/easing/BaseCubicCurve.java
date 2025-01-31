package dev.obscuria.fragmentum.core.v1.common.easing;

import com.google.common.collect.Lists;
import dev.obscuria.fragmentum.api.v1.common.easing.CubicCurve;
import dev.obscuria.fragmentum.api.v1.common.easing.EasingFunction;
import org.jetbrains.annotations.ApiStatus;

import java.util.Comparator;
import java.util.List;

@ApiStatus.Internal
public final class BaseCubicCurve implements CubicCurve
{
    private final List<Point> rawPoints = Lists.newArrayList();
    private final List<Point> cachedPoints = Lists.newArrayList();
    private final int resolution;
    private boolean dirty;

    public BaseCubicCurve(int resolution) {
        this.resolution = Math.min(Math.max(resolution, 0), 100);
        this.dirty = true;
    }

    @Override
    public BaseCubicCurve addPoint(float x, float y) {
        this.rawPoints.add(new Point(
                Math.clamp(x, 0, 1),
                Math.clamp(x, 0, 1)));
        this.rawPoints.sort(Comparator.comparingDouble(Point::x));
        this.dirty = true;
        return this;
    }

    @Override
    public Point samplePoint(float x) {
        if (this.dirty) this.bake();
        if (this.cachedPoints.isEmpty()) return Point.ZERO;
        final var index = (int) Math.clamp(x * (cachedPoints.size() - 1), 0, cachedPoints.size() - 1);
        return this.cachedPoints.get(index);
    }

    @Override
    public double sample(float x) {
        return samplePoint(x).y();
    }

    @Override
    public EasingFunction asEasing() {
        return progress -> (float) this.sample(progress);
    }

    public void bake() {
        this.dirty = false;
        this.cachedPoints.clear();

        final float res = 1f / (float) this.resolution;
        final int segments = rawPoints.size() - 1;

        for (int i = 0; i < segments; i++) {
            final var point = rawPoints.get(i);
            final var nextPoint = rawPoints.get(i + 1);
            final float xDiff = nextPoint.x() - point.x();
            final float yDiff = nextPoint.y() - point.y();

            for (float step = 0; step <= 1; step += res) {
                final float x = point.x() + step * xDiff;
                final float y = point.y() + step * step * (3 * yDiff - 2 * step * yDiff);
                this.cachedPoints.add(new Point(x, y));
            }
        }
    }
}