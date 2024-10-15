package dev.obscuria.fragmentum.api.v1.common.easing;

@FunctionalInterface
@SuppressWarnings("unused")
public interface EasingFunction
{
    float compute(float delta);

    default EasingFunction scale(float scale)
    {
        return progress -> compute(Math.clamp(progress * (1f / scale), 0f, 1f));
    }

    default EasingFunction reversed()
    {
        return progress -> 1f - compute(progress);
    }

    default EasingFunction merge(EasingFunction other)
    {
        return merge(other, 0.5f);
    }

    default EasingFunction merge(EasingFunction other, float ratio)
    {
        return progress -> progress <= ratio
                ? ratio * scale(ratio).compute(progress)
                : ratio * compute(1f) + (1f - ratio) * other.scale(1f - ratio).compute(progress - ratio);
    }

    default EasingFunction mergeOut(EasingFunction other)
    {
        return mergeOut(other, 0.5f);
    }

    default EasingFunction mergeOut(EasingFunction other, float ratio)
    {
        return progress -> progress <= ratio
                ? scale(ratio).compute(progress)
                : other.reversed().scale(1f - ratio).compute(progress - ratio);
    }
}
