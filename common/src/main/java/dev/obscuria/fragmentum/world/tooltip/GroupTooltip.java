package dev.obscuria.fragmentum.world.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record GroupTooltip(List<TooltipComponent> components) implements TooltipComponent {

    public static TooltipComponent maybeGroup(@Nullable TooltipComponent first, TooltipComponent second) {
        if (first == null) return second;
        return new GroupTooltip(List.of(first, second));
    }

    @SuppressWarnings("unchecked")
    public static <T extends TooltipComponent> @Nullable T findFirst(List<TooltipComponent> components, Class<T> type) {
        for (var component : components) {
            if (type.isInstance(component)) {
                return (T) component;
            }
            if (component instanceof GroupTooltip group) {
                final @Nullable var result = findFirst(group.components(), type);
                if (result != null) return result;
            }
        }
        return null;
    }
}
