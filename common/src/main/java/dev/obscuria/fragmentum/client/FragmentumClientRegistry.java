package dev.obscuria.fragmentum.client;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.function.Function;

@SuppressWarnings("unused")
public interface FragmentumClientRegistry
{
    static ClientRegistrar registrar(String modId)
    {
        return Fragmentum.SERVICES.client().registrar(modId);
    }

    static <T extends TooltipComponent> void registerTooltipComponent(Class<T> type, Function<T, ClientTooltipComponent> factory)
    {
        Fragmentum.SERVICES.client().registerTooltipComponent(type, factory);
    }
}
