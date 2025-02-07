package dev.obscuria.fragmentum.core.world

import net.minecraft.world.inventory.tooltip.TooltipComponent

@JvmRecord
data class AllOfTooltipComponent(val components: List<TooltipComponent>) : TooltipComponent