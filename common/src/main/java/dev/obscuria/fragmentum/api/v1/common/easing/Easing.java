package dev.obscuria.fragmentum.api.v1.common.easing;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;

/**
 * Represents a collection of predefined easing functions for animations and transitions.
 * The `Easing` enum provides various easing functions, categorized into linear, in, out,
 * in-out, and out-in types, which define how values change over time. Each easing function
 * is backed by an {@link EasingFunction} instance and can be serialized.
 */
@SuppressWarnings("unused")
public enum Easing implements EasingFunction, StringRepresentable
{
    // Linear Easing
    /**
     * Represents a linear easing function (constant transition rate).
     */
    LINEAR(EasingInternal.linear()),
    /**
     * Represents a ceiling-based easing function.
     */
    CEIL(EasingInternal.ceil()),
    /**
     * Represents a floor-based easing function.
     */
    FLOOR(EasingInternal.floor()),

    // Ease-In Functions
    /**
     * Represents an ease-in sine function.
     */
    EASE_IN_SINE(EasingInternal.easeInSine()),
    /**
     * Represents an ease-in circular function.
     */
    EASE_IN_CIRCLE(EasingInternal.easeInCircle()),
    /**
     * Represents an ease-in quadratic function.
     */
    EASE_IN_QUAD(EasingInternal.easeInQuad()),
    /**
     * Represents an ease-in cubic function.
     */
    EASE_IN_CUBIC(EasingInternal.easeInCubic()),
    /**
     * Represents an ease-in quartic function.
     */
    EASE_IN_QUART(EasingInternal.easeInQuart()),
    /**
     * Represents an ease-in quintic function.
     */
    EASE_IN_QUINT(EasingInternal.easeInQuint()),
    /**
     * Represents an ease-in exponential function.
     */
    EASE_IN_EXPO(EasingInternal.easeInExpo()),
    /**
     * Represents an ease-in back function.
     */
    EASE_IN_BACK(EasingInternal.easeInBack()),
    /**
     * Represents an ease-in elastic function.
     */
    EASE_IN_ELASTIC(EasingInternal.easeInElastic()),
    /**
     * Represents an ease-in bounce function.
     */
    EASE_IN_BOUNCE(EasingInternal.easeInBounce()),

    // Ease-Out Functions
    /**
     * Represents an ease-out sine function.
     */
    EASE_OUT_SINE(EasingInternal.easeOutSine()),
    /**
     * Represents an ease-out circular function.
     */
    EASE_OUT_CIRCLE(EasingInternal.easeOutCircle()),
    /**
     * Represents an ease-out quadratic function.
     */
    EASE_OUT_QUAD(EasingInternal.easeOutQuad()),
    /**
     * Represents an ease-out cubic function.
     */
    EASE_OUT_CUBIC(EasingInternal.easeOutCubic()),
    /**
     * Represents an ease-out quartic function.
     */
    EASE_OUT_QUART(EasingInternal.easeOutQuart()),
    /**
     * Represents an ease-out quintic function.
     */
    EASE_OUT_QUINT(EasingInternal.easeOutQuint()),
    /**
     * Represents an ease-out exponential function.
     */
    EASE_OUT_EXPO(EasingInternal.easeOutExpo()),
    /**
     * Represents an ease-out back function.
     */
    EASE_OUT_BACK(EasingInternal.easeOutBack()),
    /**
     * Represents an ease-out elastic function.
     */
    EASE_OUT_ELASTIC(EasingInternal.easeOutElastic()),
    /**
     * Represents an ease-out bounce function.
     */
    EASE_OUT_BOUNCE(EasingInternal.easeOutBounce()),

    // Ease-In-Out Functions
    /**
     * Represents an ease-in-out sine function.
     */
    EASE_IN_OUT_SINE(EasingInternal.easeInOutSine()),
    /**
     * Represents an ease-in-out circular function.
     */
    EASE_IN_OUT_CIRCLE(EasingInternal.easeInOutCircle()),
    /**
     * Represents an ease-in-out quadratic function.
     */
    EASE_IN_OUT_QUAD(EasingInternal.easeInOutQuad()),
    /**
     * Represents an ease-in-out cubic function.
     */
    EASE_IN_OUT_CUBIC(EasingInternal.easeInOutCubic()),
    /**
     * Represents an ease-in-out quartic function.
     */
    EASE_IN_OUT_QUART(EasingInternal.easeInOutQuart()),
    /**
     * Represents an ease-in-out quintic function.
     */
    EASE_IN_OUT_QUINT(EasingInternal.easeInOutQuint()),
    /**
     * Represents an ease-in-out exponential function.
     */
    EASE_IN_OUT_EXPO(EasingInternal.easeInOutExpo()),
    /**
     * Represents an ease-in-out back function.
     */
    EASE_IN_OUT_BACK(EasingInternal.easeInOutBack()),
    /**
     * Represents an ease-in-out elastic function.
     */
    EASE_IN_OUT_ELASTIC(EasingInternal.easeInOutElastic()),
    /**
     * Represents an ease-in-out bounce function.
     */
    EASE_IN_OUT_BOUNCE(EasingInternal.easeInOutBounce()),

    // Ease-Out-In Functions
    /**
     * Represents an ease-out-in sine function.
     */
    EASE_OUT_IN_SINE(EasingInternal.easeOutInSine()),
    /**
     * Represents an ease-out-in circular function.
     */
    EASE_OUT_IN_CIRCLE(EasingInternal.easeOutInCircle()),
    /**
     * Represents an ease-out-in quadratic function.
     */
    EASE_OUT_IN_QUAD(EasingInternal.easeOutInQuad()),
    /**
     * Represents an ease-out-in cubic function.
     */
    EASE_OUT_IN_CUBIC(EasingInternal.easeOutInCubic()),
    /**
     * Represents an ease-out-in quartic function.
     */
    EASE_OUT_IN_QUART(EasingInternal.easeOutInQuart()),
    /**
     * Represents an ease-out-in quintic function.
     */
    EASE_OUT_IN_QUINT(EasingInternal.easeOutInQuint()),
    /**
     * Represents an ease-out-in exponential function.
     */
    EASE_OUT_IN_EXPO(EasingInternal.easeOutInExpo()),
    /**
     * Represents an ease-out-in back function.
     */
    EASE_OUT_IN_BACK(EasingInternal.easeOutInBack()),
    /**
     * Represents an ease-out-in elastic function.
     */
    EASE_OUT_IN_ELASTIC(EasingInternal.easeOutInElastic()),
    /**
     * Represents an ease-out-in bounce function.
     */
    EASE_OUT_IN_BOUNCE(EasingInternal.easeOutInBounce());

    public static final Codec<Easing> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, Easing> STREAM_CODEC;

    private final EasingFunction function;

    Easing(EasingFunction function)
    {
        this.function = function;
    }

    /**
     * Computes the eased value for the specified input delta.
     *
     * @param delta The input value, typically in the range [0, 1].
     * @return The eased value as a float.
     */
    @Override
    public float compute(float delta)
    {
        return this.function.compute(delta);
    }

    /**
     * Returns the serialized name of the easing type.
     * <p>
     * The name is converted to lowercase format for serialization purposes.
     *
     * @return The serialized name of the easing type.
     */
    @Override
    public String getSerializedName()
    {
        return toString().toLowerCase();
    }

    static
    {
        CODEC = StringRepresentable.fromEnum(Easing::values);
        STREAM_CODEC = StreamCodec.ofMember((value, buf) -> buf.writeEnum(value),
                buf -> buf.readEnum(Easing.class));
    }
}