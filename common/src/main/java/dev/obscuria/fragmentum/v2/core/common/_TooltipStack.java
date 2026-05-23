package dev.obscuria.fragmentum.v2.core.common;

import dev.obscuria.fragmentum.v2.api.common.TooltipStack;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record _TooltipStack(List<TooltipComponent> components) implements TooltipStack {

    public static TooltipComponent maybeGroup(@Nullable TooltipComponent first, TooltipComponent second) {
        if (first == null) return second;
        return new _TooltipStack(List.of(first, second));
    }

    @SuppressWarnings("unchecked")
    public static <T extends TooltipComponent> @Nullable T findFirst(List<TooltipComponent> components, Class<T> type) {
        for (var component : components) {
            if (type.isInstance(component)) {
                return (T) component;
            }
            if (component instanceof TooltipStack group) {
                final @Nullable var result = findFirst(group.components(), type);
                if (result != null) return result;
            }
        }
        return null;
    }
}
