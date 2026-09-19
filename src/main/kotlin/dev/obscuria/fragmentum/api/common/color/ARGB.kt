package dev.obscuria.fragmentum.api.common.color

import dev.obscuria.fragmentum.api.common.CodexExtensions.withAlternatives
import com.mojang.serialization.Codec
import net.minecraft.util.Mth
import kotlin.math.roundToInt

data class ARGB(
	val alpha: Float,
	val red: Float,
	val green: Float,
	val blue: Float
) {

	fun decimal(): Int {
		val a = (alpha * 255).roundToInt().coerceIn(0, 255)
		val r = (red * 255).roundToInt().coerceIn(0, 255)
		val g = (green * 255).roundToInt().coerceIn(0, 255)
		val b = (blue * 255).roundToInt().coerceIn(0, 255)
		return (a shl 24) or (r shl 16) or (g shl 8) or b
	}

	fun hex(): String {
		return "#%08X".format(decimal())
	}

	fun components(): List<Float> {
		return listOf(alpha, red, green, blue)
	}

	fun clamped(): ARGB {
		return ARGB(
			alpha.coerceIn(0f, 1f),
			red.coerceIn(0f, 1f),
			green.coerceIn(0f, 1f),
			blue.coerceIn(0f, 1f)
		)
	}

	fun lerp(to: ARGB, delta: Float): ARGB {
		return ARGB(
			Mth.lerp(delta, alpha, to.alpha),
			Mth.lerp(delta, red, to.red),
			Mth.lerp(delta, green, to.green),
			Mth.lerp(delta, blue, to.blue)
		)
	}

	fun toRGB(): RGB {
		return RGB(red, green, blue)
	}

	fun toHSV(): HSV {
		return toRGB().toHSV()
	}

	companion object {

		val DECIMAL_CODEC: Codec<ARGB> = Codec.INT.xmap(::argbOf, ARGB::decimal)
		val HEX_CODEC: Codec<ARGB> = Codec.STRING.xmap(::argbOf, ARGB::hex)
		val NORMALIZED_CODEC: Codec<ARGB> = Codec.FLOAT.listOf().xmap(::argbOf, ARGB::components)
		val CODEC: Codec<ARGB> = HEX_CODEC.withAlternatives(DECIMAL_CODEC, NORMALIZED_CODEC)
	}
}
