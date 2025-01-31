package dev.obscuria.fragmentum.core.v1.client;

import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@ApiStatus.Internal
public final class TooltipComponentFactories
{
    private static final List<Definition<?>> definitions = Lists.newArrayList();

    public static <T extends TooltipComponent> void register(Class<T> type, Function<T, ClientTooltipComponent> factory)
    {
        if (definitions.stream().anyMatch(definition -> definition.type() == type)) return;
        definitions.add(new Definition<>(type, factory));
    }

    public static Optional<ClientTooltipComponent> create(TooltipComponent component)
    {
        for (var definition : definitions)
            if (definition.isFor(component))
                return definition.create(component);
        return Optional.empty();
    }

    private record Definition<T extends TooltipComponent>(Class<T> type, Function<T, ClientTooltipComponent> factory)
    {
        private boolean isFor(TooltipComponent component)
        {
            return component.getClass().equals(type());
        }

        private Optional<ClientTooltipComponent> create(TooltipComponent component)
        {
            return Optional.of(factory().apply(type().cast(component)));
        }
    }
}
