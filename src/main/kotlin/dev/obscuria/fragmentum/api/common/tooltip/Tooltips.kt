package dev.obscuria.fragmentum.api.common.tooltip

import dev.obscuria.fragmentum.common.tooltip.TooltipsImpl
import net.minecraft.network.chat.Component

object Tooltips {

	fun process(input: Component): List<Component> {
		return process(input.string)
	}

	fun process(input: String): List<Component> {
		return TooltipsImpl.process(input, null, TooltipOptions.DEFAULT)
	}

	fun process(input: Component, options: TooltipOptions): List<Component> {
		return process(input.string, options)
	}

	fun process(input: String, options: TooltipOptions): List<Component> {
		return TooltipsImpl.process(input, null, options)
	}

	fun process(source: Any?, input: Component): List<Component> {
		return process(source, input.string)
	}

	fun process(source: Any?, input: String): List<Component> {
		return TooltipsImpl.process(input, source, TooltipOptions.DEFAULT)
	}

	fun process(source: Any?, input: Component, options: TooltipOptions): List<Component> {
		return process(source, input.string, options)
	}

	fun process(source: Any?, input: String, options: TooltipOptions): List<Component> {
		return TooltipsImpl.process(input, source, options)
	}
}
