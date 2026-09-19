package dev.obscuria.fragmentum.api.common.easing

import com.mojang.serialization.Codec
import dev.obscuria.fragmentum.common.easing.EasingImpl
import net.minecraft.util.StringRepresentable
import java.util.*

enum class Easing(private val ease: Ease) : StringRepresentable, Ease by ease {

	LINEAR(EasingImpl.linear()),
	CEIL(EasingImpl.ceil()),
	FLOOR(EasingImpl.floor()),

	EASE_IN_SINE(EasingImpl.easeInSine()),
	EASE_IN_CIRCLE(EasingImpl.easeInCircle()),
	EASE_IN_QUAD(EasingImpl.easeInQuad()),
	EASE_IN_CUBIC(EasingImpl.easeInCubic()),
	EASE_IN_QUART(EasingImpl.easeInQuart()),
	EASE_IN_QUINT(EasingImpl.easeInQuint()),
	EASE_IN_EXPO(EasingImpl.easeInExpo()),
	EASE_IN_BACK(EasingImpl.easeInBack()),
	EASE_IN_ELASTIC(EasingImpl.easeInElastic()),
	EASE_IN_BOUNCE(EasingImpl.easeInBounce()),

	EASE_OUT_SINE(EasingImpl.easeOutSine()),
	EASE_OUT_CIRCLE(EasingImpl.easeOutCircle()),
	EASE_OUT_QUAD(EasingImpl.easeOutQuad()),
	EASE_OUT_CUBIC(EasingImpl.easeOutCubic()),
	EASE_OUT_QUART(EasingImpl.easeOutQuart()),
	EASE_OUT_QUINT(EasingImpl.easeOutQuint()),
	EASE_OUT_EXPO(EasingImpl.easeOutExpo()),
	EASE_OUT_BACK(EasingImpl.easeOutBack()),
	EASE_OUT_ELASTIC(EasingImpl.easeOutElastic()),
	EASE_OUT_BOUNCE(EasingImpl.easeOutBounce()),

	EASE_IN_OUT_SINE(EasingImpl.easeInOutSine()),
	EASE_IN_OUT_CIRCLE(EasingImpl.easeInOutCircle()),
	EASE_IN_OUT_QUAD(EasingImpl.easeInOutQuad()),
	EASE_IN_OUT_CUBIC(EasingImpl.easeInOutCubic()),
	EASE_IN_OUT_QUART(EasingImpl.easeInOutQuart()),
	EASE_IN_OUT_QUINT(EasingImpl.easeInOutQuint()),
	EASE_IN_OUT_EXPO(EasingImpl.easeInOutExpo()),
	EASE_IN_OUT_BACK(EasingImpl.easeInOutBack()),
	EASE_IN_OUT_ELASTIC(EasingImpl.easeInOutElastic()),
	EASE_IN_OUT_BOUNCE(EasingImpl.easeInOutBounce()),

	EASE_OUT_IN_SINE(EasingImpl.easeOutInSine()),
	EASE_OUT_IN_CIRCLE(EasingImpl.easeOutInCircle()),
	EASE_OUT_IN_QUAD(EasingImpl.easeOutInQuad()),
	EASE_OUT_IN_CUBIC(EasingImpl.easeOutInCubic()),
	EASE_OUT_IN_QUART(EasingImpl.easeOutInQuart()),
	EASE_OUT_IN_QUINT(EasingImpl.easeOutInQuint()),
	EASE_OUT_IN_EXPO(EasingImpl.easeOutInExpo()),
	EASE_OUT_IN_BACK(EasingImpl.easeOutInBack()),
	EASE_OUT_IN_ELASTIC(EasingImpl.easeOutInElastic()),
	EASE_OUT_IN_BOUNCE(EasingImpl.easeOutInBounce());

	override fun getSerializedName(): String {
		return name.lowercase(Locale.ROOT)
	}

	companion object {
		val CODEC: Codec<Easing> = StringRepresentable.fromEnum {
			entries.toTypedArray()
		}
	}
}
