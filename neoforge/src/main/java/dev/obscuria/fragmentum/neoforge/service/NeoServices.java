package dev.obscuria.fragmentum.neoforge.service;

import dev.obscuria.fragmentum.neoforge.registry.NeoRegistrar;
import dev.obscuria.fragmentum.content.registry.Registrar;
import dev.obscuria.fragmentum.service.*;

public final class NeoServices implements FragmentumServices {

    @Override
    public Registrar registrar(String modId) {
        return new NeoRegistrar(modId);
    }

    @Override
    public FactoryService factory() {
        return NeoFactoryService.INSTANCE;
    }

    @Override
    public NetworkService network() {
        return NeoNetworkService.INSTANCE;
    }

    @Override
    public ServerService server() {
        return NeoServerService.INSTANCE;
    }

    @Override
    public ClientService client() {
        return NeoClientService.INSTANCE;
    }

    @Override
    public ConfigService config() {
        return NeoConfigService.INSTANCE;
    }
}
