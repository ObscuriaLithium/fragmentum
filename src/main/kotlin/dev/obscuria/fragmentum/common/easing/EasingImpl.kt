package dev.obscuria.fragmentum.common.easing

import dev.obscuria.fragmentum.api.common.easing.Ease
import net.minecraft.util.Mth
import org.jetbrains.annotations.ApiStatus
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

@ApiStatus.Internal
internal object EasingImpl {

	fun linear() = Ease { it }

	fun ceil() = Ease { 1f }

	fun floor() = Ease { 0f }

	fun easeInSine() = Ease {
		1f - cos((it * Mth.PI) / 2f)
	}

	fun easeInCircle() = Ease {
		1f - sqrt((1f - it.pow(2f)))
	}

	fun easeInQuad() = Ease {
		it.pow(2f)
	}

	fun easeInCubic() = Ease {
		it.pow(3f)
	}

	fun easeInQuart() = Ease {
		it.pow(4f)
	}

	fun easeInQuint() = Ease {
		it.pow(5f)
	}

	fun easeInExpo() = Ease {
		if (it <= 0f) 0f else 2f.pow(10f * it - 10f)
	}

	fun easeInBack() = Ease {
		2.70158f * it * it * it - 1.70158f * it * it
	}

	fun easeInElastic() = Ease {
		if (it <= 0f) return@Ease 0f
		if (it >= 1f) return@Ease 1f
		(-2f).pow(10f * it - 10f) * sin((it * 10f - 10.75f) * (2f * Mth.PI / 3f))
	}

	fun easeInBounce() = Ease {
		1f - easeOutBounce().apply(1f - it)
	}

	fun easeOutSine() = Ease {
		sin((it * Mth.PI) / 2f)
	}

	fun easeOutCircle() = Ease {
		sqrt(1f - (it - 1f).pow(2f))
	}

	fun easeOutQuad() = Ease {
		1f - (it - 1f).pow(2f)
	}

	fun easeOutCubic() = Ease {
		1f + (it - 1f).pow(3f)
	}

	fun easeOutQuart() = Ease {
		1f - (it - 1f).pow(4f)
	}

	fun easeOutQuint() = Ease {
		1f + (it - 1f).pow(5f)
	}

	fun easeOutExpo() = Ease {
		if (it >= 1f) 1f else 1f - 2f.pow(-10f * it)
	}

	fun easeOutBack() = Ease {
		1f + 2.70158f * (it - 1f).pow(3f) + 1.70158f * (it - 1f).pow(2f)
	}

	fun easeOutElastic() = Ease {
		if (it <= 0f) return@Ease 0f
		if (it >= 1f) return@Ease 1f
		2f.pow(-10f * it) * sin((it * 10f - 0.75f) * (2f * Mth.PI / 3f)) + 1f
	}

	fun easeOutBounce() = Ease {
		val f1 = 7.5625f
		val f2 = 2.75f
		var prog: Float = it
		if (prog < 1f / f2) return@Ease f1 * prog * prog
		if (prog < 2f / f2) {
			prog -= 1.5f / f2
			return@Ease f1 * prog * prog + 0.75f
		}
		if (prog < 2.5f / f2) {
			prog -= 2.25f / f2
			return@Ease f1 * prog * prog + 0.9375f
		}
		prog -= 2.625f / f2
		f1 * prog * prog + 0.984375f
	}

	fun easeInOutSine(): Ease {
		return easeInSine().merge(easeOutSine(), 0.5f)
	}

	fun easeInOutCircle(): Ease {
		return easeInCircle().merge(easeOutCircle(), 0.5f)
	}

	fun easeInOutQuad(): Ease {
		return easeInQuad().merge(easeOutQuad(), 0.5f)
	}

	fun easeInOutCubic(): Ease {
		return easeInCubic().merge(easeOutCubic(), 0.5f)
	}

	fun easeInOutQuart(): Ease {
		return easeInQuart().merge(easeOutQuart(), 0.5f)
	}

	fun easeInOutQuint(): Ease {
		return easeInQuint().merge(easeOutQuint(), 0.5f)
	}

	fun easeInOutExpo(): Ease {
		return easeInExpo().merge(easeOutExpo(), 0.5f)
	}

	fun easeInOutBack(): Ease {
		return easeInBack().merge(easeOutBack(), 0.5f)
	}

	fun easeInOutElastic(): Ease {
		return easeInElastic().merge(easeOutElastic(), 0.5f)
	}

	fun easeInOutBounce(): Ease {
		return easeInBounce().merge(easeOutBounce(), 0.5f)
	}

	fun easeOutInSine(): Ease {
		return easeOutSine().merge(easeInSine(), 0.5f)
	}

	fun easeOutInCircle(): Ease {
		return easeOutCircle().merge(easeInCircle(), 0.5f)
	}

	fun easeOutInQuad(): Ease {
		return easeOutQuad().merge(easeInQuad(), 0.5f)
	}

	fun easeOutInCubic(): Ease {
		return easeOutCubic().merge(easeInCubic(), 0.5f)
	}

	fun easeOutInQuart(): Ease {
		return easeOutQuart().merge(easeInQuart(), 0.5f)
	}

	fun easeOutInQuint(): Ease {
		return easeOutQuint().merge(easeInQuint(), 0.5f)
	}

	fun easeOutInExpo(): Ease {
		return easeOutExpo().merge(easeInExpo(), 0.5f)
	}

	fun easeOutInBack(): Ease {
		return easeOutBack().merge(easeInBack(), 0.5f)
	}

	fun easeOutInElastic(): Ease {
		return easeOutElastic().merge(easeInElastic(), 0.5f)
	}

	fun easeOutInBounce(): Ease {
		return easeOutBounce().merge(easeInBounce(), 0.5f)
	}
}
