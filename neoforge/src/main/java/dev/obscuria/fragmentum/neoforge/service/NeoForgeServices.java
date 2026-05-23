package dev.obscuria.fragmentum.neoforge.service;

import dev.obscuria.fragmentum.neoforge.registry.NeoForgeRegistrar;
import dev.obscuria.fragmentum.service.*;
import dev.obscuria.fragmentum.v2.api.common.registry.Registrar;

public final class NeoForgeServices implements FragmentumServices {

    @Override public Registrar registrar(String modId) {
        return new NeoForgeRegistrar(modId);
    }

    @Override public FactoryService factory() {
        return NeoForgeFactoryService.SHARED;
    }

    @Override public NetworkService network() {
        return NeoForgeNetworkService.SHARED;
    }

    @Override public ServerService server() {
        return NeoForgeServerService.SHARED;
    }

    @Override public ClientService client() {
        return NeoForgeClientService.SHARED;
    }

    @Override public ConfigService config() {
        return NeoForgeConfigService.SHARED;
    }
}
