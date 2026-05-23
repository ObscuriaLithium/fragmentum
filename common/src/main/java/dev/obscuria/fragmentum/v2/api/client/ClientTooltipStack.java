package dev.obscuria.fragmentum.v2.api.client;

import dev.obscuria.fragmentum.v2.api.common.TooltipStack;
import dev.obscuria.fragmentum.v2.core.client._ClientTooltipStack;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
public interface ClientTooltipStack extends ClientTooltipComponent {

    static ClientTooltipStack create(TooltipStack tooltip) {
        return new _ClientTooltipStack(tooltip);
    }

    static <T extends ClientTooltipComponent> @Nullable T findFirst(List<ClientTooltipComponent> components, Class<T> type) {
        return _ClientTooltipStack.findFirst(components, type);
    }

    List<ClientTooltipComponent> components();
}
