package dev.obscuria.fragmentum.v2.api.common;

import dev.obscuria.fragmentum.v2.core.common._Easing;

@FunctionalInterface
@SuppressWarnings("unused")
public interface EasingFunction {

    float compute(float delta);

    default EasingFunction reverse() {
        return _Easing.reverse(this);
    }

    default EasingFunction scale(float scale) {
        return _Easing.scale(this, scale);
    }

    default EasingFunction merge(EasingFunction other, float ratio) {
        return _Easing.merge(this, other, ratio);
    }

    default EasingFunction mergeOut(EasingFunction other, float ratio) {
        return _Easing.mergeOut(this, other, ratio);
    }
}
