package dev.obscuria.fragmentum.neoforge.service;

import dev.obscuria.fragmentum.Fragmentum;
import dev.obscuria.fragmentum.neoforge.registry.NeoRegistrar;
import dev.obscuria.fragmentum.content.registry.Registrar;
import dev.obscuria.fragmentum.service.*;
import net.neoforged.fml.ModList;

import java.nio.file.Path;
import java.util.Optional;

public final class NeoServices implements FragmentumServices {

    @Override
    public Optional<Path> resolveRootPath(String modId) {
        var container = ModList.get().getModContainerById(modId).orElseThrow();
        final var modFile = container.getModInfo().getOwningFile().getFile();
        return Optional.of(modFile.getSecureJar().getRootPath());
    }

    @Override
    public Optional<Path> fragmentumIcon() {
        final var iconName = Fragmentum.MOD_ID + ".png";
        return resolveRootPath(Fragmentum.MOD_ID).map(it -> it.resolve(iconName));
    }

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
