package dev.obscuria.fragmentum.v2.api.common;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.v2.core.common._Easing;
import net.minecraft.util.StringRepresentable;

@SuppressWarnings("unused")
public enum Easing implements EasingFunction, StringRepresentable {
    LINEAR(_Easing.linear()),
    CEIL(_Easing.ceil()),
    FLOOR(_Easing.floor()),

    EASE_IN_SINE(_Easing.easeInSine()),
    EASE_IN_CIRCLE(_Easing.easeInCircle()),
    EASE_IN_QUAD(_Easing.easeInQuad()),
    EASE_IN_CUBIC(_Easing.easeInCubic()),
    EASE_IN_QUART(_Easing.easeInQuart()),
    EASE_IN_QUINT(_Easing.easeInQuint()),
    EASE_IN_EXPO(_Easing.easeInExpo()),
    EASE_IN_BACK(_Easing.easeInBack()),
    EASE_IN_ELASTIC(_Easing.easeInElastic()),
    EASE_IN_BOUNCE(_Easing.easeInBounce()),

    EASE_OUT_SINE(_Easing.easeOutSine()),
    EASE_OUT_CIRCLE(_Easing.easeOutCircle()),
    EASE_OUT_QUAD(_Easing.easeOutQuad()),
    EASE_OUT_CUBIC(_Easing.easeOutCubic()),
    EASE_OUT_QUART(_Easing.easeOutQuart()),
    EASE_OUT_QUINT(_Easing.easeOutQuint()),
    EASE_OUT_EXPO(_Easing.easeOutExpo()),
    EASE_OUT_BACK(_Easing.easeOutBack()),
    EASE_OUT_ELASTIC(_Easing.easeOutElastic()),
    EASE_OUT_BOUNCE(_Easing.easeOutBounce()),

    EASE_IN_OUT_SINE(_Easing.easeInOutSine()),
    EASE_IN_OUT_CIRCLE(_Easing.easeInOutCircle()),
    EASE_IN_OUT_QUAD(_Easing.easeInOutQuad()),
    EASE_IN_OUT_CUBIC(_Easing.easeInOutCubic()),
    EASE_IN_OUT_QUART(_Easing.easeInOutQuart()),
    EASE_IN_OUT_QUINT(_Easing.easeInOutQuint()),
    EASE_IN_OUT_EXPO(_Easing.easeInOutExpo()),
    EASE_IN_OUT_BACK(_Easing.easeInOutBack()),
    EASE_IN_OUT_ELASTIC(_Easing.easeInOutElastic()),
    EASE_IN_OUT_BOUNCE(_Easing.easeInOutBounce()),

    EASE_OUT_IN_SINE(_Easing.easeOutInSine()),
    EASE_OUT_IN_CIRCLE(_Easing.easeOutInCircle()),
    EASE_OUT_IN_QUAD(_Easing.easeOutInQuad()),
    EASE_OUT_IN_CUBIC(_Easing.easeOutInCubic()),
    EASE_OUT_IN_QUART(_Easing.easeOutInQuart()),
    EASE_OUT_IN_QUINT(_Easing.easeOutInQuint()),
    EASE_OUT_IN_EXPO(_Easing.easeOutInExpo()),
    EASE_OUT_IN_BACK(_Easing.easeOutInBack()),
    EASE_OUT_IN_ELASTIC(_Easing.easeOutInElastic()),
    EASE_OUT_IN_BOUNCE(_Easing.easeOutInBounce());

    public static final Codec<Easing> CODEC;
    private final EasingFunction function;

    Easing(EasingFunction function) {
        this.function = function;
    }

    @Override
    public float compute(float delta) {
        return function.compute(delta);
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }

    static {
        CODEC = StringRepresentable.fromEnum(Easing::values);
    }
}