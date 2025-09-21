@file:Suppress("unused", "RedundantNullableReturnType", "MemberVisibilityCanBePrivate")

package dev.obscuria.fragmentum.tools

import com.mojang.serialization.Codec
import dev.obscuria.fragmentum.extension.MinecraftExtension.withAlternative
import net.minecraft.util.Mth
import kotlin.math.absoluteValue

fun argbOf(decimal: Long): ARGB {
    return ARGB(decimal.toInt())
}

fun argbOf(decimal: Int): ARGB {
    return ARGB(decimal)
}

fun argbOf(hexadecimal: String): ARGB {
    val cleanHex = hexadecimal.removePrefix("#")
    val color = cleanHex.toLong(16).toInt()
    return ARGB(color)
}

fun argbOf(alpha: Float, red: Float, green: Float, blue: Float): ARGB {
    val a = (alpha * 255).toInt().coerceIn(0, 255)
    val r = (red * 255).toInt().coerceIn(0, 255)
    val g = (green * 255).toInt().coerceIn(0, 255)
    val b = (blue * 255).toInt().coerceIn(0, 255)
    val color = (a shl 24) or (r shl 16) or (g shl 8) or b
    return ARGB(color)
}

fun argbOf(normalized: FloatArray): ARGB {
    if (normalized.size != 4) throw IllegalArgumentException()
    return argbOf(normalized[0], normalized[1], normalized[2], normalized[3])
}

fun argbOf(normalized: List<Float>): ARGB {
    if (normalized.size != 4) throw IllegalArgumentException()
    return argbOf(normalized[0], normalized[1], normalized[2], normalized[3])
}

fun rgbOf(decimal: Long): RGB {
    return RGB(decimal.toInt())
}

fun rgbOf(decimal: Int): RGB {
    return RGB(decimal)
}

fun rgbOf(hexadecimal: String): RGB {
    val cleanHex = hexadecimal.removePrefix("#")
    val color = cleanHex.toInt(16) or (0xFF shl 24)
    return RGB(color)
}

fun rgbOf(red: Float, green: Float, blue: Float): RGB {
    val r = (red * 255).toInt().coerceIn(0, 255)
    val g = (green * 255).toInt().coerceIn(0, 255)
    val b = (blue * 255).toInt().coerceIn(0, 255)
    val color = (r shl 16) or (g shl 8) or b
    return RGB(color)
}

fun rgbOf(normalized: FloatArray): RGB {
    if (normalized.size != 3) throw IllegalArgumentException()
    return rgbOf(normalized[0], normalized[1], normalized[2])
}

fun rgbOf(normalized: List<Float>): RGB {
    if (normalized.size != 3) throw IllegalArgumentException()
    return rgbOf(normalized[0], normalized[1], normalized[2])
}

fun hsvOf(hue: Float, saturation: Float, value: Float): HSV {
    return HSV(floatArrayOf(hue, saturation, value))
}

fun averageRGB(entries: Map<RGB, Int>): RGB {

    if (entries.isEmpty()) return rgbOf(0)

    var totalWeight = 0
    var sumR = 0f
    var sumG = 0f
    var sumB = 0f

    for ((rgb, weight) in entries) {
        if (weight <= 0) continue
        totalWeight += weight
        sumR += rgb.component1() * weight
        sumG += rgb.component2() * weight
        sumB += rgb.component3() * weight
    }

    if (totalWeight == 0) return rgbOf(0)
    return rgbOf(sumR / totalWeight, sumG / totalWeight, sumB / totalWeight)
}

@JvmInline
value class ARGB internal constructor(val decimal: Int) {

    val int: Int get() = decimal
    val hexadecimal: String get() = String.format("#%08X", decimal)
    val normalized: FloatArray get() = floatArrayOf(component1(), component2(), component3(), component4())
    val normalizedList: List<Float> get() = listOf(component1(), component2(), component3(), component4())

    operator fun component1(): Float = ((decimal shr 24) and 0xFF) / 255f
    operator fun component2(): Float = ((decimal shr 16) and 0xFF) / 255f
    operator fun component3(): Float = ((decimal shr 8) and 0xFF) / 255f
    operator fun component4(): Float = (decimal and 0xFF) / 255f

    fun withAlpha(alpha: Float): ARGB = argbOf(alpha, component2(), component3(), component4())

    fun toRGB(): RGB = rgbOf(decimal and 0xFFFFFF)

    companion object {

        private val DECIMAL: Codec<ARGB> = Codec.INT.xmap(::argbOf, ARGB::decimal)
        private val HEXADECIMAL: Codec<ARGB> = Codec.STRING.xmap(::argbOf, ARGB::hexadecimal)
        private val NORMALIZED: Codec<ARGB> = Codec.FLOAT.listOf().xmap(::argbOf, ARGB::normalizedList)
        val CODEC: Codec<ARGB> = DECIMAL.withAlternative(HEXADECIMAL).withAlternative(NORMALIZED)
    }
}

@JvmInline
value class RGB internal constructor(val decimal: Int) {

    val int: Int get() = decimal
    val hexadecimal: String get() = String.format("#%06X", decimal and 0xFFFFFF)
    val normalized: FloatArray get() = floatArrayOf(component1(), component2(), component3())
    val normalizedList: List<Float> get() = listOf(component1(), component2(), component3())

    operator fun component1(): Float = ((decimal shr 16) and 0xFF) / 255f
    operator fun component2(): Float = ((decimal shr 8) and 0xFF) / 255f
    operator fun component3(): Float = (decimal and 0xFF) / 255f

    fun shiftHue(offset: Float): RGB = toHSV().shiftHue(offset).toRGB()

    fun shiftSaturation(offset: Float): RGB = toHSV().shiftSaturation(offset).toRGB()

    fun shiftValue(offset: Float): RGB = toHSV().shiftValue(offset).toRGB()

    fun toARGB(alpha: Float = 1.0f): ARGB = argbOf(alpha, component1(), component2(), component3())

    fun toHSV(): HSV {
        val (r, g, b) = this

        val max = maxOf(r, g, b)
        val min = minOf(r, g, b)
        val delta = max - min

        val hue = when (max) {
            r -> (g - b) / delta
            g -> (b - r) / delta + 2f
            else -> (r - g) / delta + 4f
        } * (1f / 6f)

        val saturation = if (max == 0f) 0f else delta / max

        return hsvOf(hue.mod(1f), saturation, max)
    }

    fun lerp(delta: Float, to: RGB): RGB {
        val r = Mth.lerp(delta, component1(), to.component1())
        val g = Mth.lerp(delta, component2(), to.component2())
        val b = Mth.lerp(delta, component3(), to.component3())
        return rgbOf(r, g, b)
    }

    companion object {

        private val DECIMAL: Codec<RGB> = Codec.INT.xmap(::rgbOf, RGB::decimal)
        private val HEXADECIMAL: Codec<RGB> = Codec.STRING.xmap(::rgbOf, RGB::hexadecimal)
        private val NORMALIZED: Codec<RGB> = Codec.FLOAT.listOf().xmap(::rgbOf, RGB::normalizedList)
        val CODEC: Codec<RGB> = DECIMAL.withAlternative(HEXADECIMAL).withAlternative(NORMALIZED)
    }
}

@JvmInline
value class HSV internal constructor(val data: FloatArray) {

    val hue: Float get() = data[0]
    val saturation: Float get() = data[1]
    val value: Float get() = data[2]

    operator fun component1(): Float = hue
    operator fun component2(): Float = saturation
    operator fun component3(): Float = value

    fun shiftHue(offset: Float): HSV {
        val newHue = ((hue + offset) % 1f).let { if (it < 0) it + 1f else it }
        return HSV(floatArrayOf(newHue, saturation, value))
    }

    fun shiftSaturation(offset: Float): HSV {
        val newSaturation = ((saturation + offset) % 1f).let { if (it < 0) it + 1f else it }
        return HSV(floatArrayOf(hue, newSaturation, value))
    }

    fun shiftValue(offset: Float): HSV {
        val newValue = ((value + offset) % 1f).let { if (it < 0) it + 1f else it }
        return HSV(floatArrayOf(hue, saturation, newValue))
    }

    fun toRGB(): RGB {
        val hueSegment = (hue * 6).toInt()
        val chroma = value * saturation
        val secondary = chroma * (1 - ((hue * 6) % 2 - 1).absoluteValue)
        val match = value - chroma

        var red = 0f
        var green = 0f
        var blue = 0f

        when (hueSegment) {
            0 -> { red = chroma; green = secondary }
            1 -> { red = secondary; green = chroma }
            2 -> { green = chroma; blue = secondary }
            3 -> { green = secondary; blue = chroma }
            4 -> { red = secondary; blue = chroma }
            5, 6 -> { red = chroma; blue = secondary }
        }

        return rgbOf(red + match, green + match, blue + match)
    }
}