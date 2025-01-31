package dev.obscuria.fragmentum.api.v1.common;

import dev.obscuria.fragmentum.api.v1.common.event.Event;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public interface FragmentumEvents
{
    Event<GatherTooltipImages> GATHER_TOOLTIP_IMAGES = Event.create();

    @FunctionalInterface
    interface GatherTooltipImages
    {
        void invoke(ItemStack stack, Consumer<TooltipComponent> consumer);
    }
}
