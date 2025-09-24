package dev.obscuria.fragmentum;

@SuppressWarnings("unused")
public interface Platform
{
    String getEnvironmentName();

    boolean isModLoaded(String modId);

    boolean isClient();

    boolean isDedicatedServer();

    boolean isDevelopmentEnvironment();
}