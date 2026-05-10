package dev.obscuria.fragmentum;

import java.nio.file.Path;

@SuppressWarnings("unused")
public interface Platform {

    Path getConfigDir();

    String getEnvironmentName();

    boolean isModLoaded(String modId);

    boolean isClient();

    boolean isDedicatedServer();

    boolean isDevelopmentEnvironment();
}