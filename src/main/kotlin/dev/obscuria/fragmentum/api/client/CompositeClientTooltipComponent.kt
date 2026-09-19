package dev.obscuria.fragmentum.api.client

import dev.obscuria.fragmentum.api.common.tooltip.CompositeTooltipComponent
import dev.obscuria.fragmentum.client.CompositeClientTooltipComponentImpl
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent

interface CompositeClientTooltipComponent : ClientTooltipComponent {

	val components: List<ClientTooltipComponent>

	companion object {

		fun create(component: CompositeTooltipComponent): CompositeClientTooltipComponent {
			return CompositeClientTooltipComponentImpl.create(component)
		}

		inline fun <reified T : ClientTooltipComponent> findFirst(component: CompositeClientTooltipComponent): T? {
			return findFirst(T::class.java, component)
		}

		inline fun <reified T : ClientTooltipComponent> findFirst(components: List<ClientTooltipComponent?>): T? {
			return findFirst(T::class.java, components)
		}

		fun <T : ClientTooltipComponent> findFirst(type: Class<T>, component: CompositeClientTooltipComponent): T? {
			return findFirst(type, component.components)
		}

		fun <T : ClientTooltipComponent> findFirst(type: Class<T>, components: List<ClientTooltipComponent?>): T? {
			return CompositeClientTooltipComponentImpl.findFirst(type, components)
		}
	}
}
