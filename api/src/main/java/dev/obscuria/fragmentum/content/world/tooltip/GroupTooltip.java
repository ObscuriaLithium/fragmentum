package dev.obscuria.fragmentum.content.world.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
public record GroupTooltip(List<TooltipComponent> components) implements TooltipComponent {

    public static TooltipComponent maybeGroup(@Nullable TooltipComponent first, TooltipComponent second) {
        throw new NotImplementedException();
    }

    public static <T extends TooltipComponent> @Nullable T findFirst(List<TooltipComponent> components, Class<T> type) {
        throw new NotImplementedException();
    }
}
