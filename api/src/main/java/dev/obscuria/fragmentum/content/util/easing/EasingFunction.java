package dev.obscuria.fragmentum.content.util.easing;

import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("unused")
@FunctionalInterface
public interface EasingFunction
{
    float compute(float delta);

    default EasingFunction reverse()
    {
        throw new NotImplementedException();
    }

    default EasingFunction scale(float scale)
    {
        throw new NotImplementedException();
    }

    default EasingFunction merge(EasingFunction other, float ratio)
    {
        throw new NotImplementedException();
    }

    default EasingFunction mergeOut(EasingFunction other, float ratio)
    {
        throw new NotImplementedException();
    }
}
