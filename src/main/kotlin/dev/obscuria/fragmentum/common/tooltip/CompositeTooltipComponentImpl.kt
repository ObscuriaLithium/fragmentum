package dev.obscuria.fragmentum.common.tooltip

import dev.obscuria.fragmentum.api.common.tooltip.CompositeTooltipComponent
import net.minecraft.world.inventory.tooltip.TooltipComponent
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal data class CompositeTooltipComponentImpl(
	override val components: List<TooltipComponent?>
) : CompositeTooltipComponent {

	companion object {

		fun of(components: Collection<TooltipComponent>): CompositeTooltipComponentImpl? {
			if (components.isEmpty()) return null
			return CompositeTooltipComponentImpl(components.toList())
		}

		fun of(vararg components: TooltipComponent?): CompositeTooltipComponentImpl? {
			if (components.isEmpty()) return null
			return CompositeTooltipComponentImpl(listOf(*components))
		}
	}
}
