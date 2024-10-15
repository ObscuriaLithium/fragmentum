package dev.obscuria.fragmentum.neoforge.config;

import dev.obscuria.fragmentum.api.ObscureAPI;
import dev.obscuria.fragmentum.api.v1.common.config.IConfigSpec;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class NeoConfigSpec implements IConfigSpec {
    private final ModConfigSpec configSpec;

    public NeoConfigSpec(ModConfigSpec configSpec) {
        this.configSpec = configSpec;
    }

    @Override
    public void registerClient(String directory, String name) {
        this.createDirectory(directory);
        this.registerClient("%s/%s".formatted(directory, name));
    }

    @Override
    public void registerCommon(String directory, String name) {
        this.createDirectory(directory);
        this.registerCommon("%s/%s".formatted(directory, name));
    }

    @Override
    public void registerServer(String directory, String name) {
        this.createDirectory(directory);
        this.registerServer("%s/%s".formatted(directory, name));
    }

    @Override
    public void registerClient(String name) {
        final var container = ModLoadingContext.get().getActiveContainer();
        container.registerConfig(
                ModConfig.Type.CLIENT, configSpec,
                "%s-client.toml".formatted(name));
    }

    @Override
    public void registerCommon(String name) {
        final var container = ModLoadingContext.get().getActiveContainer();
        container.registerConfig(
                ModConfig.Type.COMMON, configSpec,
                "%s-common.toml".formatted(name));
    }

    @Override
    public void registerServer(String name) {
        final var container = ModLoadingContext.get().getActiveContainer();
        container.registerConfig(
                ModConfig.Type.SERVER, configSpec,
                "%s-server.toml".formatted(name));
    }

    private void createDirectory(String directory) {
        var path = Paths.get(FMLPaths.CONFIGDIR.get().toAbsolutePath().toString(), directory);
        try {
            Files.createDirectory(path);
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            ObscureAPI.LOG.error("Failed to create config directory: {}", directory, e);
        }
    }
}
