package dev.obscuria.fragmentum.api;

public interface IPlatform
{
    String getPlatformName();

    boolean isModLoaded(String modId);

    boolean isClient();

    default boolean isDedicatedServer()
    {
        return !isClient();
    }

    boolean isDevelopmentEnvironment();
}