package dev.obscuria.fragmentum.api.common.color

import com.mojang.serialization.Codec
import net.minecraft.util.Mth
import kotlin.math.abs

data class HSV(
	val hue: Float,
	val saturation: Float,
	val value: Float
) {

	fun components(): List<Float> {
		return listOf(hue, saturation, value)
	}

	fun shiftHue(offset: Float): HSV {
		return copy(hue = (hue + offset).mod(1f))
	}

	fun shiftSaturation(offset: Float): HSV {
		return copy(saturation = (saturation + offset).coerceIn(0f, 1f))
	}

	fun shiftValue(offset: Float): HSV {
		return copy(value = (value + offset).coerceIn(0f, 1f))
	}

	fun clamped(): HSV {
		return HSV(
			hue.mod(1f),
			saturation.coerceIn(0f, 1f),
			value.coerceIn(0f, 1f)
		)
	}

	fun lerp(to: HSV, delta: Float): HSV {
		return HSV(
			Mth.lerp(delta, hue, to.hue),
			Mth.lerp(delta, saturation, to.saturation),
			Mth.lerp(delta, value, to.value)
		)
	}

	fun toRGB(): RGB {
		val h = hue.mod(1f) * 6f
		val segment = h.toInt()
		val chroma = value * saturation
		val secondary = chroma * (1f - abs(h % 2f - 1f))
		val match = value - chroma
		val (red, green, blue) = when (segment) {
			0 -> Triple(chroma, secondary, 0f)
			1 -> Triple(secondary, chroma, 0f)
			2 -> Triple(0f, chroma, secondary)
			3 -> Triple(0f, secondary, chroma)
			4 -> Triple(secondary, 0f, chroma)
			else -> Triple(chroma, 0f, secondary)
		}
		return RGB(
			red + match,
			green + match,
			blue + match
		)
	}

	fun toARGB(alpha: Float = 1f): ARGB  {
		return toRGB().toARGB(alpha)
	}

	companion object {

		val CODEC: Codec<HSV> = Codec.FLOAT.listOf().xmap(::hsvOf, HSV::components)
	}
}
