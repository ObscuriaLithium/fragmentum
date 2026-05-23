package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.fabric.registry.FabricRegistrar;
import dev.obscuria.fragmentum.service.*;
import dev.obscuria.fragmentum.v2.api.common.registry.Registrar;

public final class FabricServices implements FragmentumServices {

    @Override
    public Registrar registrar(String modId) {
        return new FabricRegistrar(modId);
    }

    @Override
    public FactoryService factory() {
        return FabricFactoryService.SHARED;
    }

    @Override
    public NetworkService network() {
        return FabricNetworkService.SHARED;
    }

    @Override
    public ServerService server() {
        return FabricServerService.SHARED;
    }

    @Override
    public ClientService client() {
        return FabricClientService.SHARED;
    }

    @Override
    public ConfigService config() {
        return FabricConfigService.SHARED;
    }
}
