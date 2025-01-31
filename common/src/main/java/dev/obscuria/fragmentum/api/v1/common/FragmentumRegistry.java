package dev.obscuria.fragmentum.api.v1.common;

/**
 * A registry interface for managing custom global and mod-specific registries in the Fragmentum framework.
 */
@SuppressWarnings("unused")
public interface FragmentumRegistry
{
    /**
     * Retrieves an {@link IRegistrar} instance for managing registrations specific to a mod.
     *
     * @param modId The unique identifier of the mod (namespace).
     * @return An {@link IRegistrar} instance for registering mod-specific objects.
     */
    static IRegistrar registrar(String modId)
    {
        return V1Common.INSTANCE.registrar(modId);
    }
}