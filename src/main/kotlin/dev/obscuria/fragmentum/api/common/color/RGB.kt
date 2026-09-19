package dev.obscuria.fragmentum.api.common.color

import dev.obscuria.fragmentum.api.common.CodexExtensions.withAlternatives
import com.mojang.serialization.Codec
import net.minecraft.util.Mth
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

data class RGB(
	val red: Float,
	val green: Float,
	val blue: Float
) {

	fun decimal(): Int {
		val r = (red * 255).roundToInt().coerceIn(0, 255)
		val g = (green * 255).roundToInt().coerceIn(0, 255)
		val b = (blue * 255).roundToInt().coerceIn(0, 255)
		return (r shl 16) or (g shl 8) or b
	}

	fun hex(): String {
		return "#%06X".format(decimal())
	}

	fun components(): List<Float> {
		return listOf(red, green, blue)
	}

	fun clamped(): RGB {
		return RGB(
			red.coerceIn(0f, 1f),
			green.coerceIn(0f, 1f),
			blue.coerceIn(0f, 1f)
		)
	}

	fun lerp(to: RGB, delta: Float): RGB {
		return RGB(
			Mth.lerp(delta, red, to.red),
			Mth.lerp(delta, green, to.green),
			Mth.lerp(delta, blue, to.blue)
		)
	}

	fun toARGB(alpha: Float = 1f): ARGB {
		return ARGB(alpha, red, green, blue)
	}

	fun toHSV(): HSV {
		val max = max(red, max(green, blue))
		val min = min(red, min(green, blue))
		val delta = max - min
		val hue = when {
			delta == 0f -> 0f
			max == red -> (green - blue) / delta
			max == green -> (blue - red) / delta + 2f
			else -> (red - green) / delta + 4f
		}.let { (it / 6f).mod(1f) }
		val saturation =
			if (max == 0f) 0f
			else delta / max
		return HSV(hue, saturation, max)
	}

	companion object {

		val DECIMAL_CODEC: Codec<RGB> = Codec.INT.xmap(::rgbOf, RGB::decimal)
		val HEX_CODEC: Codec<RGB> = Codec.STRING.xmap(::rgbOf, RGB::hex)
		val NORMALIZED_CODEC: Codec<RGB> = Codec.FLOAT.listOf().xmap(::rgbOf, RGB::components)
		val CODEC: Codec<RGB> = HEX_CODEC.withAlternatives(DECIMAL_CODEC, NORMALIZED_CODEC)
	}
}
