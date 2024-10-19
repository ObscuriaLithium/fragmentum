package dev.obscuria.fragmentum.api.v1.server;

import org.jetbrains.annotations.ApiStatus;

import java.util.ServiceLoader;

@ApiStatus.Internal
public interface V1Server
{
    V1Server INSTANCE = ServiceLoader.load(V1Server.class).findFirst().orElseThrow();

    void registerCommand(FragmentumServerRegistry.CommandRegistrar registrar);
}
