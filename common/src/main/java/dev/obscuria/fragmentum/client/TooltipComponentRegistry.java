package dev.obscuria.fragmentum.client;

import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class TooltipComponentRegistry {

    private static final List<Definition<?>> definitions = new ArrayList<>();

    public static <T extends TooltipComponent> void register(Class<T> type, Function<T, ClientTooltipComponent> factory) {
        if (definitions.stream().anyMatch(it -> it.type.equals(type))) return;
        definitions.add(new Definition<>(type, factory));
    }

    public static Optional<ClientTooltipComponent> create(TooltipComponent component) {
        for (var definition : definitions)
            if (definition.isFor(component))
                return Optional.of(definition.create(component));
        return Optional.empty();
    }

    private record Definition<T extends TooltipComponent>(Class<T> type, Function<T, ClientTooltipComponent> factory) {

        public boolean isFor(TooltipComponent component) {
            return component.getClass().equals(type);
        }

        public ClientTooltipComponent create(TooltipComponent component) {
            return factory.apply(type.cast(component));
        }
    }
}
