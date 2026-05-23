package dev.obscuria.fragmentum.v2.api.client;

import dev.obscuria.fragmentum.v2.core.client._TooltipComponentRegistry;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class TooltipComponentRegistry {

    public static <T extends TooltipComponent> void register(Class<T> type, Function<T, ClientTooltipComponent> factory) {
        _TooltipComponentRegistry.register(type, factory);
    }

    public static Optional<ClientTooltipComponent> create(TooltipComponent component) {
        return _TooltipComponentRegistry.create(component);
    }
}
