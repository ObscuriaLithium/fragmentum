package dev.obscuria.fragmentum.neoforge.service;

import dev.obscuria.fragmentum.neoforge.registry.NeoForgeClientRegistrar;
import dev.obscuria.fragmentum.service.ClientService;
import dev.obscuria.fragmentum.v2.api.client.ClientRegistrar;

public final class NeoForgeClientService implements ClientService {

    public static final NeoForgeClientService SHARED = new NeoForgeClientService();

    private NeoForgeClientService() {}

    @Override public ClientRegistrar registrar(String modId) {
        return new NeoForgeClientRegistrar(modId);
    }
}
