package dev.obscuria.fragmentum.world

import net.minecraft.world.inventory.tooltip.TooltipComponent

@JvmRecord
data class AllOfTooltipComponent(val components: List<TooltipComponent>) : TooltipComponent