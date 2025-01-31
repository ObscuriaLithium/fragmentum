package dev.obscuria.fragmentum.api;

/**
 * An interface that defines platform-specific functionality for determining the runtime environment
 * and loading status of mods in a Minecraft-based platform.
 */
@SuppressWarnings("unused")
public interface IPlatform
{
    /**
     * Retrieves the name of the platform the application is running on.
     *
     * @return A {@link String} representing the name of the platform.
     */
    String getPlatformName();

    /**
     * Checks if a specific mod is currently loaded on the platform.
     *
     * @param modId The identifier of the mod to check.
     * @return {@code true} if the mod with the given ID is loaded, {@code false} otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Determines whether the application is running on the client-side environment.
     *
     * @return {@code true} if the platform is running in a client environment, {@code false} otherwise.
     */
    boolean isClient();

    /**
     * Determines whether the application is running on a dedicated server environment.
     * <p>
     * By default, this method returns the inverse of {@link #isClient()}.
     *
     * @return {@code true} if the platform is running in a dedicated server environment, {@code false} otherwise.
     */
    default boolean isDedicatedServer()
    {
        return !isClient();
    }

    /**
     * Checks if the application is running in a development environment.
     * This can be used to distinguish between production and dev setups.
     *
     * @return {@code true} if the platform is in a development environment, {@code false} otherwise.
     */
    boolean isDevelopmentEnvironment();
}