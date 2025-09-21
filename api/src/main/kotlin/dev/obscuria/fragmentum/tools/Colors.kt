@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.tools

import com.mojang.serialization.Codec

fun argbOf(decimal: Long): ARGB = error("API stub")

fun argbOf(decimal: Int): ARGB = error("API stub")

fun argbOf(hexadecimal: String): ARGB = error("API stub")

fun argbOf(alpha: Float, red: Float, green: Float, blue: Float): ARGB = error("API stub")

fun argbOf(normalized: FloatArray): ARGB = error("API stub")

fun argbOf(normalized: List<Float>): ARGB = error("API stub")

fun rgbOf(decimal: Long): RGB = error("API stub")

fun rgbOf(decimal: Int): RGB = error("API stub")

fun rgbOf(hexadecimal: String): RGB = error("API stub")

fun rgbOf(red: Float, green: Float, blue: Float): RGB = error("API stub")

fun rgbOf(normalized: FloatArray): RGB = error("API stub")

fun rgbOf(normalized: List<Float>): RGB = error("API stub")

fun hsvOf(hue: Float, saturation: Float, value: Float): HSV = error("API stub")

fun averageRGB(entries: Map<RGB, Int>): RGB = error("API stub")

@JvmInline
value class ARGB internal constructor(val decimal: Int) {

    val int: Int get() = error("API stub")
    val hexadecimal: String get() = error("API stub")
    val normalized: FloatArray get() = error("API stub")
    val normalizedList: List<Float> get() = error("API stub")

    operator fun component1(): Float = error("API stub")
    operator fun component2(): Float = error("API stub")
    operator fun component3(): Float = error("API stub")
    operator fun component4(): Float = error("API stub")

    fun withAlpha(alpha: Float): ARGB = error("API stub")

    fun toRGB(): RGB = error("API stub")

    companion object {

        private val DECIMAL: Codec<ARGB> = error("API stub")
        private val HEXADECIMAL: Codec<ARGB> = error("API stub")
        private val NORMALIZED: Codec<ARGB> = error("API stub")
        val CODEC: Codec<ARGB> = error("API stub")
    }
}

@JvmInline
value class RGB internal constructor(val decimal: Int) {

    val int: Int get() = error("API stub")
    val hexadecimal: String get() = error("API stub")
    val normalized: FloatArray get() = error("API stub")
    val normalizedList: List<Float> get() = error("API stub")

    operator fun component1(): Float = error("API stub")
    operator fun component2(): Float = error("API stub")
    operator fun component3(): Float = error("API stub")

    fun shiftHue(offset: Float): RGB = error("API stub")

    fun shiftSaturation(offset: Float): RGB = error("API stub")

    fun shiftValue(offset: Float): RGB = error("API stub")

    fun toARGB(alpha: Float = 1.0f): ARGB = error("API stub")

    fun toHSV(): HSV = error("API stub")

    fun lerp(delta: Float, to: RGB): RGB = error("API stub")

    companion object {

        private val DECIMAL: Codec<RGB> = error("API stub")
        private val HEXADECIMAL: Codec<RGB> = error("API stub")
        private val NORMALIZED: Codec<RGB> = error("API stub")
        val CODEC: Codec<RGB> = error("API stub")
    }
}

@JvmInline
value class HSV internal constructor(val data: FloatArray) {

    val hue: Float get() = error("API stub")
    val saturation: Float get() = error("API stub")
    val value: Float get() = error("API stub")

    operator fun component1(): Float = error("API stub")
    operator fun component2(): Float = error("API stub")
    operator fun component3(): Float = error("API stub")

    fun shiftHue(offset: Float): HSV = error("API stub")

    fun shiftSaturation(offset: Float): HSV = error("API stub")

    fun shiftValue(offset: Float): HSV = error("API stub")

    fun toRGB(): RGB = error("API stub")
}