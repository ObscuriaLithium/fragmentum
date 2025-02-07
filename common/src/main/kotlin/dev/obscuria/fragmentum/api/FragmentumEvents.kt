package dev.obscuria.fragmentum.api

import dev.obscuria.fragmentum.api.tools.event.Event
import net.minecraft.world.inventory.tooltip.TooltipComponent
import net.minecraft.world.item.ItemStack
import java.util.function.Consumer

object FragmentumEvents
{
    val GATHER_TOOLTIP_IMAGES: Event<GatherTooltipImages> = Event.create()

    fun interface GatherTooltipImages
    {
        fun invoke(stack: ItemStack, consumer: Consumer<TooltipComponent>)
    }
}