package dev.obscuria.fragmentum.client

import dev.obscuria.fragmentum.api.client.CompositeClientTooltipComponent
import dev.obscuria.fragmentum.api.common.tooltip.CompositeTooltipComponent
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent
import net.minecraft.client.renderer.MultiBufferSource
import org.jetbrains.annotations.ApiStatus
import org.joml.Matrix4f

@ApiStatus.Internal
internal data class CompositeClientTooltipComponentImpl(
	override val components: List<ClientTooltipComponent>
) : CompositeClientTooltipComponent {

	override fun getHeight(): Int {
		return components.sumOf {
			it.height
		}
	}

	override fun getWidth(font: Font): Int {
		return components.maxOf {
			it.getWidth(font)
		}
	}

	override fun renderImage(
		font: Font,
		x: Int,
		y: Int,
		graphics: GuiGraphics
	) {
		var offset = 0
		components.forEach {
			it.renderImage(font, x, y + offset, graphics)
			offset += it.height
		}
	}

	override fun renderText(
		font: Font,
		mouseX: Int,
		mouseY: Int,
		matrix: Matrix4f,
		source: MultiBufferSource.BufferSource
	) {
		var offset = 0
		components.forEach {
			it.renderText(font, mouseX, mouseY + offset, matrix, source)
			offset += it.height
		}
	}

	companion object {

		fun create(component: CompositeTooltipComponent): CompositeClientTooltipComponentImpl {
			val components = component.components.filterNotNull().map(ClientTooltipComponent::create)
			return CompositeClientTooltipComponentImpl(components)
		}

		fun <T : ClientTooltipComponent> findFirst(type: Class<T>, components: List<ClientTooltipComponent?>): T? {
			return components.firstNotNullOfOrNull {
				when {
					type.isInstance(it) -> type.cast(it)
					it is CompositeClientTooltipComponent -> findFirst(type, it.components)
					else -> null
				}
			}
		}
	}
}
