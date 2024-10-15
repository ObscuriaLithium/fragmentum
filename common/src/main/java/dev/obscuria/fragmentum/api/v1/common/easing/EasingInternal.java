package dev.obscuria.fragmentum.api.v1.common.easing;

final class EasingInternal
{
    public static EasingFunction linear() {
        return progress -> progress;
    }

    public static EasingFunction ceil() {
        return progress -> 1;
    }

    public static EasingFunction floor() {
        return progress -> 0;
    }

    public static EasingFunction easeInSine() {
        return progress -> 1f - (float) Math.cos((progress * Math.PI) / 2f);
    }

    public static EasingFunction easeInCircle() {
        return progress -> 1f - (float) Math.sqrt(1f - Math.pow(progress, 2f));
    }

    public static EasingFunction easeInQuad() {
        return progress -> (float) Math.pow(progress, 2);
    }

    public static EasingFunction easeInCubic() {
        return progress -> (float) Math.pow(progress, 3);
    }

    public static EasingFunction easeInQuart() {
        return progress -> (float) Math.pow(progress, 4);
    }

    public static EasingFunction easeInQuint() {
        return progress -> (float) Math.pow(progress, 5);
    }

    public static EasingFunction easeInExpo() {
        return progress -> progress == 0 ? 0 : (float) Math.pow(2f, 10f * progress - 10f);
    }

    public static EasingFunction easeInBack() {
        return progress -> 2.70158f * progress * progress * progress - 1.70158f * progress * progress;
    }

    public static EasingFunction easeInElastic() {
        return progress -> progress == 0 ? 0 : (progress == 1 ? 1 : (float) (-Math.pow(2f, 10f * progress - 10f) * Math.sin((progress * 10f - 10.75f) * ((2f * Math.PI) / 3f))));
    }

    public static EasingFunction easeInBounce() {
        return progress -> 1f - easeOutBounce().compute(1f - progress);
    }

    public static EasingFunction easeOutSine() {
        return progress -> (float) Math.sin((progress * Math.PI) / 2f);
    }

    public static EasingFunction easeOutCircle() {
        return progress -> (float) Math.sqrt(1f - Math.pow(progress - 1f, 2f));
    }

    public static EasingFunction easeOutQuad() {
        return progress -> 1f - (float) Math.pow(progress - 1f, 2);
    }

    public static EasingFunction easeOutCubic() {
        return progress -> 1f + (float) Math.pow(progress - 1f, 3);
    }

    public static EasingFunction easeOutQuart() {
        return progress -> 1f - (float) Math.pow(progress - 1f, 4);
    }

    public static EasingFunction easeOutQuint() {
        return progress -> 1f + (float) Math.pow(progress - 1f, 5);
    }

    public static EasingFunction easeOutExpo() {
        return progress -> progress == 1 ? 1 : 1f - (float) Math.pow(2f, -10f * progress);
    }

    public static EasingFunction easeOutBack() {
        return progress -> 1f + 2.70158f * (float) Math.pow(progress - 1f, 3f) + 1.70158f * (float) Math.pow(progress - 1f, 2f);
    }

    public static EasingFunction easeOutElastic() {
        return progress -> progress == 0 ? 0 : progress == 1 ? 1 : (float) (Math.pow(2f, -10f * progress) * Math.sin((progress * 10f - 0.75f) * ((2f * Math.PI) / 3f)) + 1f);
    }

    public static EasingFunction easeOutBounce() {
        return progress -> {
            float f1 = 7.5625f;
            float f2 = 2.75f;
            if (progress < 1f / f2) return f1 * progress * progress;
            else if (progress < 2 / f2) return f1 * (progress -= 1.5f / f2) * progress + 0.75f;
            else if (progress < 2.5 / f2) return f1 * (progress -= 2.25f / f2) * progress + 0.9375f;
            else return f1 * (progress -= 2.625f / f2) * progress + 0.984375f;
        };
    }

    public static EasingFunction easeInOutSine() {
        return easeInSine().merge(easeOutSine());
    }

    public static EasingFunction easeInOutCircle() {
        return easeInCircle().merge(easeOutCircle());
    }

    public static EasingFunction easeInOutQuad() {
        return easeInQuad().merge(easeOutQuad());
    }

    public static EasingFunction easeInOutCubic() {
        return easeInCubic().merge(easeOutCubic());
    }

    public static EasingFunction easeInOutQuart() {
        return easeInQuart().merge(easeOutQuart());
    }

    public static EasingFunction easeInOutQuint() {
        return easeInQuint().merge(easeOutQuint());
    }

    public static EasingFunction easeInOutExpo() {
        return easeInExpo().merge(easeOutExpo());
    }

    public static EasingFunction easeInOutBack() {
        return easeInBack().merge(easeOutBack());
    }

    public static EasingFunction easeInOutElastic() {
        return easeInElastic().merge(easeOutElastic());
    }

    public static EasingFunction easeInOutBounce() {
        return easeInBounce().merge(easeOutBounce());
    }

    public static EasingFunction easeOutInSine() {
        return easeOutSine().merge(easeInSine());
    }

    public static EasingFunction easeOutInCircle() {
        return easeOutCircle().merge(easeInCircle());
    }

    public static EasingFunction easeOutInQuad() {
        return easeOutQuad().merge(easeInQuad());
    }

    public static EasingFunction easeOutInCubic() {
        return easeOutCubic().merge(easeInCubic());
    }

    public static EasingFunction easeOutInQuart() {
        return easeOutQuart().merge(easeInQuart());
    }

    public static EasingFunction easeOutInQuint() {
        return easeOutQuint().merge(easeInQuint());
    }

    public static EasingFunction easeOutInExpo() {
        return easeOutExpo().merge(easeInExpo());
    }

    public static EasingFunction easeOutInBack() {
        return easeOutBack().merge(easeInBack());
    }

    public static EasingFunction easeOutInElastic() {
        return easeOutElastic().merge(easeInElastic());
    }

    public static EasingFunction easeOutInBounce() {
        return easeOutBounce().merge(easeInBounce());
    }
}
