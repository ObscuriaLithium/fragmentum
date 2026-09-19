package dev.obscuria.fragmentum.api.common.tooltip

import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.Style

data class TooltipOptions(
    val prefixByLine: Map<Int, String>,
    val styleByLine: Map<Int, Style>,
    val processor: (String) -> MutableComponent,
    val defaultPrefix: String,
    val defaultStyle: Style,
    val maxLineLength: Int
) {

	fun getLinePrefix(index: Int): String {
		return prefixByLine[index] ?: defaultPrefix
	}

	fun getLineStyle(index: Int): Style {
		return styleByLine[index] ?: defaultStyle
	}

	class Builder {

		private val prefixByLine = mutableMapOf<Int, String>()
		private val styleByLine = mutableMapOf<Int, Style>()
		private var processor: (String) -> MutableComponent = Component::literal
		private var defaultPrefix: String = ""
		private var defaultStyle: Style = Style.EMPTY
		private var maxLineLength: Int = 40

		fun withLineSpecificPrefix(line: Int, prefix: String) = apply {
			this.prefixByLine[line] = prefix
		}

		fun withLineSpecificStyle(line: Int, style: Style) = apply {
			this.styleByLine[line] = style
		}

		fun withProcessor(processor: (String) -> MutableComponent) = apply {
			this.processor = processor
		}

		fun withDefaultPrefix(prefix: String) = apply {
			this.defaultPrefix = prefix
		}

		fun withDefaultStyle(style: Style) = apply {
			this.defaultStyle = style
		}

		fun withMaxLineLength(maxLineLength: Int) = apply {
			this.maxLineLength = maxLineLength
		}

		fun build() = TooltipOptions(
			prefixByLine.toMap(), styleByLine.toMap(), processor,
			defaultPrefix, defaultStyle, maxLineLength
		)
	}

	companion object {

		fun builder(): Builder {
			return Builder()
		}

		val DEFAULT: TooltipOptions = builder().build()
		val DESCRIPTION: TooltipOptions = builder().withDefaultStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)).build()
		val LORE: TooltipOptions = builder().withDefaultStyle(Style.EMPTY.withColor(ChatFormatting.LIGHT_PURPLE)).build()
		val ERROR: TooltipOptions = builder().withDefaultStyle(Style.EMPTY.withColor(ChatFormatting.RED)).build()
	}
}
