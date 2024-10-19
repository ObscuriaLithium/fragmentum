package dev.obscuria.fragmentum.api.v1.client;

import org.jetbrains.annotations.ApiStatus;

import java.util.ServiceLoader;

@ApiStatus.Internal
public interface V1Client
{
    V1Client INSTANCE = ServiceLoader.load(V1Client.class).findFirst().orElseThrow();

    IClientRegistrar registrar(String modId);
}
