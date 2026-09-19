package dev.obscuria.fragmentum.api.config.format

import net.minecraft.network.chat.Component

fun interface DoubleFormatter {

	fun format(value: Double): Component

	fun formatInt(value: Int): Component {
		return format(value.toDouble())
	}
}
