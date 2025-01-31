package dev.obscuria.fragmentum.api.v1.common.easing;

/**
 * Represents a functional interface for easing functions, which calculate smooth transitions
 * between values typically used in animations.
 * <p>
 * The `EasingFunction` takes an input progress value (typically between 0 and 1) as a delta
 * and computes the corresponding eased value. Additionally, it provides methods to transform
 * or combine easing functions.
 */
@FunctionalInterface
@SuppressWarnings("unused")
public interface EasingFunction
{
    /**
     * Computes the eased value for the given progress.
     *
     * @param delta A progress value in the range [0, 1], where 0 represents the start and 1
     *              represents the end of the transition.
     * @return The eased value as a {@code float}.
     */
    float compute(float delta);

    /**
     * Scales the easing function to alter its progression by a given scale factor.
     * <p>
     * This is useful for compressing or expanding the duration of the easing progression.
     *
     * @param scale A scaling factor where values greater than 1 slow down the progression
     *              and values less than 1 speed it up.
     * @return A new scaled {@code EasingFunction}.
     */
    default EasingFunction scale(float scale)
    {
        return EasingInternal.scale(this, scale);
    }

    /**
     * Reverses the easing function, flipping its behavior such that the result is inverted.
     * <p>
     * This is useful for creating mirrored or backward animations.
     *
     * @return A new reversed {@code EasingFunction}.
     */
    default EasingFunction reversed()
    {
        return EasingInternal.reversed(this);
    }

    /**
     * Combines this easing function with another, interpolating between the two with an
     * equal ratio.
     * <p>
     * This creates a composite easing function that transitions between the two functions.
     *
     * @param other The other {@code EasingFunction} to merge with.
     * @return A new merged {@code EasingFunction}.
     */
    default EasingFunction merge(EasingFunction other)
    {
        return merge(other, 0.5f);
    }

    /**
     * Combines this easing function with another, interpolating between the two with
     * a specified ratio.
     * <p>
     * When progress is less than or equal to the ratio, this function dominates. When progress
     * exceeds the ratio, the other function takes effect.
     *
     * @param other The other {@code EasingFunction} to merge with.
     * @param ratio The ratio at which the transition between the two functions occurs, in the range [0, 1].
     * @return A new merged {@code EasingFunction}.
     */
    default EasingFunction merge(EasingFunction other, float ratio)
    {
        return EasingInternal.merge(this, other, ratio);
    }

    /**
     * Combines this easing function with another such that the first half follows this function
     * and the second half reverses the other function.
     * <p>
     * This creates a seamless transition between the two functions for smoother results.
     *
     * @param other The other {@code EasingFunction} to merge into.
     * @return A new merged {@code EasingFunction}.
     */
    default EasingFunction mergeOut(EasingFunction other)
    {
        return mergeOut(other, 0.5f);
    }

    /**
     * Combines this easing function with another with a specified ratio, such that the behavior
     * transitions from this function to the reversed behavior of the other.
     *
     * @param other The other {@code EasingFunction} to merge into.
     * @param ratio The ratio at which the transition between the two functions occurs, in the range [0, 1].
     * @return A new merged {@code EasingFunction}.
     */
    default EasingFunction mergeOut(EasingFunction other, float ratio)
    {
        return EasingInternal.mergeOut(this, other, ratio);
    }
}