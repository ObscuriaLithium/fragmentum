package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.fabric.registry.FabricRegistrar;
import dev.obscuria.fragmentum.registry.Registrar;
import dev.obscuria.fragmentum.service.*;

public class FabricServices implements FragmentumServices {

    @Override
    public Registrar registrar(String modId) {
        return new FabricRegistrar(modId);
    }

    @Override
    public FactoryService factory() {
        return FabricFactoryService.INSTANCE;
    }

    @Override
    public NetworkService network() {
        return FabricNetworkService.INSTANCE;
    }

    @Override
    public ServerService server() {
        return FabricServerService.INSTANCE;
    }

    @Override
    public ClientService client() {
        return FabricClientService.INSTANCE;
    }

    @Override
    public ConfigService config() {
        return FabricConfigService.INSTANCE;
    }
}
