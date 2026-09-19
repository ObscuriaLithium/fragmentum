package dev.obscuria.fragmentum.api.config.format

import net.minecraft.network.chat.Component
import kotlin.math.roundToInt

object DoubleFormatters {

	val RAW = DoubleFormatter { Component.literal("%.3f".format(it).trimEnd('0').trimEnd('.')) }
	val POTENCY = DoubleFormatter { Component.translatable("config.fragmentum.format.potency.${it.roundToInt()}") }
	val PERCENTAGE = DoubleFormatter { Component.translatable("config.fragmentum.format.percentage", RAW.format(it * 100)) }
	val SECONDS = DoubleFormatter { Component.translatable("config.fragmentum.format.seconds", RAW.format(it)) }
	val MINUTES = DoubleFormatter { Component.translatable("config.fragmentum.format.minutes", RAW.format(it)) }
	val BLOCKS = DoubleFormatter { Component.translatable("config.fragmentum.format.blocks", it.toInt()) }
	val CHUNKS = DoubleFormatter { Component.translatable("config.fragmentum.format.chunks", it.toInt()) }
}
