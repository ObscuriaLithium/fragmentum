package dev.obscuria.fragmentum.api.v1.common;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * A utility interface for managing interactions between mods.
 */
@SuppressWarnings("unused")
public interface ModBridge
{
    /**
     * Creates a bridge for a mod with the given identifier.
     *
     * @param id The unique identifier of the mod (namespace).
     * @return A {@link ModBridge} instance for interacting with the specified mod.
     */
    static ModBridge create(String id)
    {
        return V1Common.INSTANCE.newModBridge(id, id);
    }

    /**
     * Creates a bridge for a mod with the given identifier and display name.
     *
     * @param id          The unique identifier of the mod (namespace).
     * @param displayName The human-readable name of the mod, used for display purposes.
     * @return A {@link ModBridge} instance for interacting with the specified mod.
     */
    static ModBridge create(String id, String displayName)
    {
        return V1Common.INSTANCE.newModBridge(id, displayName);
    }

    /**
     * Retrieves the identifier of the mod associated with this bridge.
     *
     * @return A {@link String} representing the unique identifier of the mod.
     */
    String id();

    /**
     * Retrieves the display name of the mod associated with this bridge.
     *
     * @return A {@link String} representing the human-readable name of the mod.
     */
    String displayName();

    /**
     * Checks if the mod associated with this bridge is loaded.
     *
     * @return {@code true} if the mod is loaded, {@code false} otherwise.
     */
    boolean isLoaded();

    /**
     * Safely retrieves a resource or functionality provided by the mod if it is loaded.
     *
     * @param <T>      The type of the resource or functionality.
     * @param supplier A {@link Supplier} that provides access to the mod-specific supplier of the resource or functionality.
     * @return An {@link Optional} containing the resource or functionality if it is available and the mod is loaded.
     */
    <T> Optional<T> safeGet(Supplier<Supplier<T>> supplier);

    /**
     * Safely executes a task defined by a {@link Runnable} if the mod is loaded.
     *
     * @param supplier A {@link Supplier} that provides the {@link Runnable} to be executed.
     */
    void safeRun(Supplier<Runnable> supplier);

    /**
     * Executes a fallback {@link Runnable} if the mod associated with this bridge is missing or not loaded.
     *
     * @param runnable The {@link Runnable} to execute when the mod is missing.
     */
    void ifMissing(Runnable runnable);
}