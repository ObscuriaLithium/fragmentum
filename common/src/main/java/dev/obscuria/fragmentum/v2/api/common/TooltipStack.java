package dev.obscuria.fragmentum.v2.api.common;

import dev.obscuria.fragmentum.v2.core.common._TooltipStack;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
public interface TooltipStack extends TooltipComponent {

    static TooltipComponent maybeGroup(@Nullable TooltipComponent first, TooltipComponent second) {
        return _TooltipStack.maybeGroup(first, second);
    }

    static <T extends TooltipComponent> @Nullable T findFirst(List<TooltipComponent> components, Class<T> type) {
        return _TooltipStack.findFirst(components, type);
    }

    List<TooltipComponent> components();
}
