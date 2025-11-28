package dev.obscuria.fragmentum.content.util.easing;

import net.minecraft.util.Mth;

@SuppressWarnings("unused")
@FunctionalInterface
public interface EasingFunction {

    float compute(float delta);

    default EasingFunction reverse() {
        return progress -> 1f - this.compute(progress);
    }

    default EasingFunction scale(float scale) {
        return progress -> this.compute(Mth.clamp(progress * (1f / scale), 0f, 1f));
    }

    default EasingFunction merge(EasingFunction other, float ratio) {
        return progress -> {
            if (progress <= ratio) return ratio * this.scale(ratio).compute(progress);
            return ratio * this.compute(1f) + (1f - ratio) * other.scale(1f - ratio).compute(progress - ratio);
        };
    }

    default EasingFunction mergeOut(EasingFunction other, float ratio) {
        return progress -> {
            if (progress <= ratio) return ratio * this.scale(ratio).compute(progress);
            return other.reverse().scale(1f - ratio).compute(progress - ratio);
        };
    }
}
