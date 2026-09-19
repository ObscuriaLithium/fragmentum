package dev.obscuria.fragmentum.api.common.tooltip

import dev.obscuria.fragmentum.common.tooltip.CompositeTooltipComponentImpl
import net.minecraft.world.inventory.tooltip.TooltipComponent

interface CompositeTooltipComponent : TooltipComponent {

	val components: List<TooltipComponent?>

	companion object {

		fun of(components: Collection<TooltipComponent>): TooltipComponent? {
			return CompositeTooltipComponentImpl.of(components)
		}

		fun of(vararg components: TooltipComponent?): TooltipComponent? {
			return CompositeTooltipComponentImpl.of(*components)
		}
	}
}
