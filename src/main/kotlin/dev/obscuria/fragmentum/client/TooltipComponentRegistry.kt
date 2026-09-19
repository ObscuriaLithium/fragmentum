package dev.obscuria.fragmentum.client

import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent
import net.minecraft.world.inventory.tooltip.TooltipComponent
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal object TooltipComponentRegistry {

	private val definitions = mutableMapOf<Class<*>, Definition<*>>()

	fun <T : TooltipComponent> register(type: Class<T>, factory: (T) -> ClientTooltipComponent) {
		definitions[type] = Definition(type, factory)
	}

	fun create(component: TooltipComponent): ClientTooltipComponent? {
		return definitions[component.javaClass]?.create(component)
	}

	private data class Definition<T : TooltipComponent>(
		val type: Class<T>,
		val factory: (T) -> ClientTooltipComponent
	) {
		fun create(component: TooltipComponent): ClientTooltipComponent {
			return factory(type.cast(component))
		}
	}
}
