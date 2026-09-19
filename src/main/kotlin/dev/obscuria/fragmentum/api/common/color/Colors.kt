package dev.obscuria.fragmentum.api.common.color

fun argbOf(alpha: Float, red: Float, green: Float, blue: Float): ARGB {
	return ARGB(alpha, red, green, blue)
}

fun argbOf(decimal: Int): ARGB {
	return ARGB(
		((decimal shr 24) and 0xFF) / 255f,
		((decimal shr 16) and 0xFF) / 255f,
		((decimal shr 8) and 0xFF) / 255f,
		(decimal and 0xFF) / 255f
	)
}

fun argbOf(hexadecimal: String): ARGB {
	val hex = hexadecimal.replace("#", "")
	return argbOf(hex.toLong(16).toInt())
}

fun argbOf(components: List<Float>): ARGB {
	require(components.size == 4)
	return argbOf(components[0], components[1], components[2], components[3])
}

fun rgbOf(red: Float, green: Float, blue: Float): RGB {
	return RGB(red, green, blue)
}

fun rgbOf(decimal: Int): RGB {
	return RGB(
		((decimal shr 16) and 0xFF) / 255f,
		((decimal shr 8) and 0xFF) / 255f,
		(decimal and 0xFF) / 255f
	)
}

fun rgbOf(hexadecimal: String): RGB {
	val hex = hexadecimal.replace("#", "")
	return rgbOf(hex.toInt(16) or (0xFF shl 24))
}

fun rgbOf(normalized: MutableList<Float>): RGB {
	require(normalized.size == 3)
	return rgbOf(normalized[0], normalized[1], normalized[2])
}

fun hsvOf(hue: Float, saturation: Float, value: Float): HSV {
	return HSV(hue, saturation, value)
}

fun hsvOf(normalized: MutableList<Float>): HSV {
	require(normalized.size == 3)
	return hsvOf(normalized[0], normalized[1], normalized[2])
}
