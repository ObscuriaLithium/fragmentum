package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.api.v1.client.IClientRegistrar;
import dev.obscuria.fragmentum.api.v1.client.V1Client;

public final class FabricV1Client implements V1Client
{
    @Override
    public IClientRegistrar registrar(String modId)
    {
        return new FabricClientRegistrar(modId);
    }
}
