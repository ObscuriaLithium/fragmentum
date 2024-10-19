package dev.obscuria.fragmentum.forge.service;

import dev.obscuria.fragmentum.api.v1.client.IClientRegistrar;
import dev.obscuria.fragmentum.api.v1.client.V1Client;

public final class ForgeV1Client implements V1Client
{
    @Override
    public IClientRegistrar registrar(String modId)
    {
        return new ForgeClientRegistrar(modId);
    }
}
