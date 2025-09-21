package dev.obscuria.fragmentum.tools.easing

import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

internal object EasingInternal {

    fun linear(): EasingFunction = EasingFunction { progress ->
        progress
    }

    fun ceil(): EasingFunction = EasingFunction { _ ->
        1f
    }

    fun floor(): EasingFunction = EasingFunction { _ ->
        0f
    }

    fun easeInSine(): EasingFunction = EasingFunction { progress ->
        1f - cos((progress * Math.PI) / 2f).toFloat()
    }

    fun easeInCircle(): EasingFunction = EasingFunction { progress ->
        1f - sqrt(1f - progress.pow(2f))
    }

    fun easeInQuad(): EasingFunction = EasingFunction { progress ->
        progress.pow(2f)
    }

    fun easeInCubic(): EasingFunction = EasingFunction { progress ->
        progress.pow(3f)
    }

    fun easeInQuart(): EasingFunction = EasingFunction { progress ->
        progress.pow(4f)
    }

    fun easeInQuint(): EasingFunction = EasingFunction { progress ->
        progress.pow(5f)
    }

    fun easeInExpo(): EasingFunction = EasingFunction { progress ->
        if (progress <= 0) 0f
        else 2.0.pow((10f * progress - 10f).toDouble()).toFloat()
    }

    fun easeInBack(): EasingFunction = EasingFunction { progress ->
        2.70158f * progress * progress * progress - 1.70158f * progress * progress
    }

    fun easeInElastic(): EasingFunction = EasingFunction { progress ->
        if (progress <= 0) 0f
        else if (progress >= 1) 1f
        else {
            (-2.0).pow((10f * progress - 10f).toDouble()).toFloat() * sin(
                (progress * 10f - 10.75f) * ((2f * Math.PI) / 3f)
            ).toFloat()
        }
    }

    fun easeInBounce(): EasingFunction = EasingFunction { progress ->
        1f - easeOutBounce().compute(1f - progress)
    }

    fun easeOutSine(): EasingFunction = EasingFunction { progress ->
        sin((progress * Math.PI) / 2f).toFloat()
    }

    fun easeOutCircle(): EasingFunction = EasingFunction { progress ->
        sqrt(1f - (progress - 1f).toDouble().pow(2.0)).toFloat()
    }

    fun easeOutQuad(): EasingFunction = EasingFunction { progress ->
        1f - (progress - 1f).toDouble().pow(2.0).toFloat()
    }

    fun easeOutCubic(): EasingFunction = EasingFunction { progress ->
        1f + (progress - 1f).toDouble().pow(3.0).toFloat()
    }

    fun easeOutQuart(): EasingFunction = EasingFunction { progress ->
        1f - (progress - 1f).toDouble().pow(4.0).toFloat()
    }

    fun easeOutQuint(): EasingFunction = EasingFunction { progress ->
        1f + (progress - 1f).toDouble().pow(5.0).toFloat()
    }

    fun easeOutExpo(): EasingFunction = EasingFunction { progress ->
        if (progress >= 1) 1f
        else 1f - 2.0.pow((-10f * progress).toDouble()).toFloat()
    }

    fun easeOutBack(): EasingFunction = EasingFunction { progress ->
        1f + 2.70158f * (progress - 1f).toDouble().pow(3.0).toFloat() +
                1.70158f * (progress - 1f).toDouble().pow(2.0).toFloat()
    }

    fun easeOutElastic(): EasingFunction = EasingFunction { progress ->
        if (progress <= 0) 0f
        else if (progress >= 1) 1f
        else {
            (2.0.pow((-10f * progress).toDouble()) * sin(
                (progress * 10f - 0.75f) * ((2f * Math.PI) / 3f)
            ) + 1f).toFloat()
        }
    }

    fun easeOutBounce(): EasingFunction = EasingFunction { progress ->
        var prog = progress
        val f1 = 7.5625f
        val f2 = 2.75f
        when {
            prog < 1f / f2 -> f1 * prog * prog
            prog < 2 / f2 -> {
                prog -= 1.5f / f2
                f1 * prog * prog + 0.75f
            }

            prog < 2.5 / f2 -> {
                prog -= 2.25f / f2
                f1 * prog * prog + 0.9375f
            }

            else -> {
                prog -= 2.625f / f2
                f1 * prog * prog + 0.984375f
            }
        }
    }

    fun easeInOutSine(): EasingFunction = easeInSine().merge(easeOutSine())

    fun easeInOutCircle(): EasingFunction = easeInCircle().merge(easeOutCircle())

    fun easeInOutQuad(): EasingFunction = easeInQuad().merge(easeOutQuad())

    fun easeInOutCubic(): EasingFunction = easeInCubic().merge(easeOutCubic())

    fun easeInOutQuart(): EasingFunction = easeInQuart().merge(easeOutQuart())

    fun easeInOutQuint(): EasingFunction = easeInQuint().merge(easeOutQuint())

    fun easeInOutExpo(): EasingFunction = easeInExpo().merge(easeOutExpo())

    fun easeInOutBack(): EasingFunction = easeInBack().merge(easeOutBack())

    fun easeInOutElastic(): EasingFunction = easeInElastic().merge(easeOutElastic())

    fun easeInOutBounce(): EasingFunction = easeInBounce().merge(easeOutBounce())

    fun easeOutInSine(): EasingFunction = easeOutSine().merge(easeInSine())

    fun easeOutInCircle(): EasingFunction = easeOutCircle().merge(easeInCircle())

    fun easeOutInQuad(): EasingFunction = easeOutQuad().merge(easeInQuad())

    fun easeOutInCubic(): EasingFunction = easeOutCubic().merge(easeInCubic())

    fun easeOutInQuart(): EasingFunction = easeOutQuart().merge(easeInQuart())

    fun easeOutInQuint(): EasingFunction = easeOutQuint().merge(easeInQuint())

    fun easeOutInExpo(): EasingFunction = easeOutExpo().merge(easeInExpo())

    fun easeOutInBack(): EasingFunction = easeOutBack().merge(easeInBack())

    fun easeOutInElastic(): EasingFunction = easeOutElastic().merge(easeInElastic())

    fun easeOutInBounce(): EasingFunction = easeOutBounce().merge(easeInBounce())
}