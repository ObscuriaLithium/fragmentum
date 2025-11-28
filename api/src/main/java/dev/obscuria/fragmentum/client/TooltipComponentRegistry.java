package dev.obscuria.fragmentum.client;

import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class TooltipComponentRegistry {

    public static <T extends TooltipComponent> void register(Class<T> type, Function<T, ClientTooltipComponent> factory) {
        throw new NotImplementedException();
    }

    public static Optional<ClientTooltipComponent> create(TooltipComponent component) {
        throw new NotImplementedException();
    }
}
