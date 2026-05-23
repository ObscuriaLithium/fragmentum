package dev.obscuria.fragmentum.neoforge;

import dev.obscuria.fragmentum.Platform;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public final class NeoForgePlatform implements Platform {

    @Override public Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override public String getEnvironmentName() {
        return "NeoForge";
    }

    @Override public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override public boolean isDevelopmentEnvironment() {
        return !FMLLoader.getCurrent().isProduction();
    }

    @Override public boolean isClient() {
        return FMLEnvironment.getDist().isClient();
    }

    @Override public boolean isDedicatedServer() {
        return FMLEnvironment.getDist().isDedicatedServer();
    }
}