package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.fabric.registry.FabricRegistrar;
import dev.obscuria.fragmentum.registry.Registrar;
import dev.obscuria.fragmentum.service.*;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.nio.file.Path;
import java.util.Optional;

public class FabricServices implements FragmentumServices {

    @Override
    public Optional<Path> resolveRootPath(String modId) {
        return FabricLoader.getInstance()
                .getModContainer(modId)
                .map(ModContainer::getRootPath);
    }

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
