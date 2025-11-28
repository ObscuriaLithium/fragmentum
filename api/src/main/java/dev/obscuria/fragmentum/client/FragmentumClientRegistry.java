package dev.obscuria.fragmentum.client;

import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Function;

@SuppressWarnings("unused")
public interface FragmentumClientRegistry {

    static ClientRegistrar registrar(String modId) {
        throw new NotImplementedException();
    }

    static <T extends TooltipComponent> void registerTooltipComponent(Class<T> type, Function<T, ClientTooltipComponent> factory) {
        throw new NotImplementedException();
    }
}
