package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.fabric.registry.FabricClientRegistrar;
import dev.obscuria.fragmentum.service.ClientService;
import dev.obscuria.fragmentum.v2.api.client.ClientRegistrar;

public final class FabricClientService implements ClientService {

    public static final FabricClientService SHARED = new FabricClientService();

    @Override public ClientRegistrar registrar(String modId) {
        return new FabricClientRegistrar(modId);
    }

    private FabricClientService() {}
}
