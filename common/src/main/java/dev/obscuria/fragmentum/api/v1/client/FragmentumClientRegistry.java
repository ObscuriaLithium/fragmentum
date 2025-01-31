package dev.obscuria.fragmentum.api.v1.client;

import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.function.Function;

/**
 * A client-side registry interface for registering client-specific components and behavior in the Fragmentum framework.
 */
@SuppressWarnings("unused")
public interface FragmentumClientRegistry
{
    /**
     * Retrieves an {@link IClientRegistrar} instance for the specified mod ID.
     * This is used to register client-specific components such as models, renderers, or textures
     * for the provided namespace.
     *
     * @param modId The unique identifier for the mod (namespace).
     * @return An {@link IClientRegistrar} instance associated with the specified mod ID.
     */
    static IClientRegistrar registrar(String modId)
    {
        return V1Client.INSTANCE.registrar(modId);
    }

    /**
     * Registers a custom tooltip component factory, allowing the creation of client-side visualizations
     * for custom {@link TooltipComponent} objects.
     *
     * @param <T>     The type of the custom tooltip component being registered.
     * @param type    The {@link Class} of the {@link TooltipComponent} to register.
     * @param factory A {@link Function} that maps the custom tooltip component to a {@link ClientTooltipComponent}.
     */
    static <T extends TooltipComponent> void registerTooltipComponent(Class<T> type, Function<T, ClientTooltipComponent> factory)
    {
        V1Client.INSTANCE.registerTooltipComponent(type, factory);
    }
}