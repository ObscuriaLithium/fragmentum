package dev.obscuria.fragmentum.v2.core.common;

import dev.obscuria.fragmentum.v2.api.common.EasingFunction;
import lombok.experimental.UtilityClass;

import static java.lang.Math.*;

@UtilityClass
public final class _Easing {

    public static EasingFunction reverse(EasingFunction input) {
        return progress -> 1f - input.compute(progress);
    }

    public static EasingFunction scale(EasingFunction input, float scale) {
        return progress -> input.compute(progress * (1f / scale));
    }

    public static EasingFunction merge(EasingFunction input, EasingFunction other, float ratio) {
        return progress -> {
            if (progress <= ratio) {
                final var local = progress / ratio;
                return input.compute(local) * ratio;
            } else {
                final var local = (progress - ratio) / (1f - ratio);
                return ratio + other.compute(local) * (1f - ratio);
            }
        };
    }

    public static EasingFunction mergeOut(EasingFunction input, EasingFunction other, float ratio) {
        return progress -> {
            if (progress <= ratio) {
                final var local = progress / ratio;
                return input.compute(local);
            } else {
                final var local = (progress - ratio) / (1f - ratio);
                return 1f - other.compute(local);
            }
        };
    }

    public static EasingFunction linear() {
        return progress -> progress;
    }

    public static EasingFunction ceil() {
        return _ -> 1f;
    }

    public static EasingFunction floor() {
        return _ -> 0f;
    }

    public static EasingFunction easeInSine() {
        return progress -> 1f - (float) cos((progress * PI) / 2f);
    }

    public static EasingFunction easeInCircle() {
        return progress -> 1f - (float) sqrt(1f - (float) pow(progress, 2f));
    }

    public static EasingFunction easeInQuad() {
        return progress -> (float) pow(progress, 2f);
    }

    public static EasingFunction easeInCubic() {
        return progress -> (float) pow(progress, 3f);
    }

    public static EasingFunction easeInQuart() {
        return progress -> (float) pow(progress, 4f);
    }

    public static EasingFunction easeInQuint() {
        return progress -> (float) pow(progress, 5f);
    }

    public static EasingFunction easeInExpo() {
        return progress -> progress <= 0f ? 0f : (float) pow(2.0, 10f * progress - 10f);
    }

    public static EasingFunction easeInBack() {
        return progress -> 2.70158f * progress * progress * progress - 1.70158f * progress * progress;
    }

    public static EasingFunction easeInElastic() {
        return progress -> {
            if (progress <= 0f) return 0f;
            if (progress >= 1f) return 1f;
            return (float) (pow(-2.0, 10f * progress - 10f) * sin((progress * 10f - 10.75f) * ((2f * PI) / 3f)));
        };
    }

    public static EasingFunction easeInBounce() {
        return progress -> 1f - easeOutBounce().compute(1f - progress);
    }

    public static EasingFunction easeOutSine() {
        return progress -> (float) sin((progress * PI) / 2f);
    }

    public static EasingFunction easeOutCircle() {
        return progress -> (float) sqrt(1f - pow(progress - 1f, 2.0));
    }

    public static EasingFunction easeOutQuad() {
        return progress -> 1f - (float) pow(progress - 1f, 2.0);
    }

    public static EasingFunction easeOutCubic() {
        return progress -> 1f + (float) pow(progress - 1f, 3.0);
    }

    public static EasingFunction easeOutQuart() {
        return progress -> 1f - (float) pow(progress - 1f, 4.0);
    }

    public static EasingFunction easeOutQuint() {
        return progress -> 1f + (float) pow(progress - 1f, 5.0);
    }

    public static EasingFunction easeOutExpo() {
        return progress -> progress >= 1f ? 1f : 1f - (float) pow(2.0, -10f * progress);
    }

    public static EasingFunction easeOutBack() {
        return progress -> 1f + 2.70158f * (float) pow(progress - 1f, 3.0) + 1.70158f * (float) pow(progress - 1f, 2.0);
    }

    public static EasingFunction easeOutElastic() {
        return progress -> {
            if (progress <= 0f) return 0f;
            if (progress >= 1f) return 1f;
            return (float) (pow(2.0, -10f * progress) * sin((progress * 10f - 0.75f) * ((2f * PI) / 3f)) + 1f);
        };
    }

    public static EasingFunction easeOutBounce() {
        return progress -> {
            float f1 = 7.5625f;
            float f2 = 2.75f;
            float prog = progress;
            if (prog < 1f / f2) return f1 * prog * prog;
            if (prog < 2f / f2) {
                prog -= 1.5f / f2;
                return f1 * prog * prog + 0.75f;
            }
            if (prog < 2.5f / f2) {
                prog -= 2.25f / f2;
                return f1 * prog * prog + 0.9375f;
            }
            prog -= 2.625f / f2;
            return f1 * prog * prog + 0.984375f;
        };
    }

    public static EasingFunction easeInOutSine() {
        return easeInSine().merge(easeOutSine(), 0.5f);
    }

    public static EasingFunction easeInOutCircle() {
        return easeInCircle().merge(easeOutCircle(), 0.5f);
    }

    public static EasingFunction easeInOutQuad() {
        return easeInQuad().merge(easeOutQuad(), 0.5f);
    }

    public static EasingFunction easeInOutCubic() {
        return easeInCubic().merge(easeOutCubic(), 0.5f);
    }

    public static EasingFunction easeInOutQuart() {
        return easeInQuart().merge(easeOutQuart(), 0.5f);
    }

    public static EasingFunction easeInOutQuint() {
        return easeInQuint().merge(easeOutQuint(), 0.5f);
    }

    public static EasingFunction easeInOutExpo() {
        return easeInExpo().merge(easeOutExpo(), 0.5f);
    }

    public static EasingFunction easeInOutBack() {
        return easeInBack().merge(easeOutBack(), 0.5f);
    }

    public static EasingFunction easeInOutElastic() {
        return easeInElastic().merge(easeOutElastic(), 0.5f);
    }

    public static EasingFunction easeInOutBounce() {
        return easeInBounce().merge(easeOutBounce(), 0.5f);
    }

    public static EasingFunction easeOutInSine() {
        return easeOutSine().merge(easeInSine(), 0.5f);
    }

    public static EasingFunction easeOutInCircle() {
        return easeOutCircle().merge(easeInCircle(), 0.5f);
    }

    public static EasingFunction easeOutInQuad() {
        return easeOutQuad().merge(easeInQuad(), 0.5f);
    }

    public static EasingFunction easeOutInCubic() {
        return easeOutCubic().merge(easeInCubic(), 0.5f);
    }

    public static EasingFunction easeOutInQuart() {
        return easeOutQuart().merge(easeInQuart(), 0.5f);
    }

    public static EasingFunction easeOutInQuint() {
        return easeOutQuint().merge(easeInQuint(), 0.5f);
    }

    public static EasingFunction easeOutInExpo() {
        return easeOutExpo().merge(easeInExpo(), 0.5f);
    }

    public static EasingFunction easeOutInBack() {
        return easeOutBack().merge(easeInBack(), 0.5f);
    }

    public static EasingFunction easeOutInElastic() {
        return easeOutElastic().merge(easeInElastic(), 0.5f);
    }

    public static EasingFunction easeOutInBounce() {
        return easeOutBounce().merge(easeInBounce(), 0.5f);
    }
}