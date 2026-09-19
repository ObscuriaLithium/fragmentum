package dev.obscuria.fragmentum.common.tooltip

import dev.obscuria.fragmentum.api.common.color.RGB
import dev.obscuria.fragmentum.api.common.tooltip.StyleFlag
import dev.obscuria.fragmentum.api.common.tooltip.TooltipOptions
import net.minecraft.network.chat.CommonComponents
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import org.jetbrains.annotations.ApiStatus
import java.util.EnumSet

@ApiStatus.Internal
internal class TooltipBuilder(
	private val options: TooltipOptions
) {

	private val lines = mutableListOf<Component>()
	private val colors = mutableListOf<RGB>()
	private val flags: MutableSet<StyleFlag> = EnumSet.noneOf(StyleFlag::class.java)

	private var line: MutableComponent = Component.empty()
	private var lineLength = 0
	private var isLineEmpty = true

	fun pushColor(color: RGB) {
		colors.add(color)
	}

	fun popColor() {
		if (colors.isEmpty()) return
		colors.removeAt(colors.lastIndex)
	}

	fun pushFlag(flag: StyleFlag) {
		flags.add(flag)
	}

	fun popFlag(flag: StyleFlag) {
		flags.remove(flag)
	}

	fun breakLine() {
		flush()
	}

	fun prepareForAppend(length: Int) {
		if (lineLength + length + 1 > options.maxLineLength) flush()
	}

	fun maybeAppendSpacing() {
		if (!isLineEmpty) {
			line.append(CommonComponents.SPACE)
			lineLength += 1
		}
	}

	fun append(length: Int, component: MutableComponent) {
		line.append(applyStyle(component))
		lineLength += length
		isLineEmpty = false
	}

	fun result(): List<Component> {
		if (!isLineEmpty) flush()
		return lines.toList()
	}

	private fun applyStyle(component: MutableComponent): MutableComponent {
		for (color in colors) component.withStyle { it.withColor(color.decimal()) }
		if (StyleFlag.ITALIC in flags) component.withStyle { it.withItalic(true) }
		if (StyleFlag.NON_ITALIC in flags) component.withStyle { it.withItalic(false) }
		if (StyleFlag.BOLD in flags) component.withStyle { it.withBold(true) }
		if (StyleFlag.NON_BOLD in flags) component.withStyle { it.withBold(false) }
		return component
	}

	private fun flush() {
		lines.add(line.withStyle(options.getLineStyle(lines.size)))
		line = Component.empty()
		isLineEmpty = true
		lineLength = 0
	}
}
