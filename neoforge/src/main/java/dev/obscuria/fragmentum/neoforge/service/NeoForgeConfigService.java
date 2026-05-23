package dev.obscuria.fragmentum.neoforge.service;

import dev.obscuria.fragmentum.service.ConfigService;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class NeoForgeConfigService implements ConfigService {

    public static final NeoForgeConfigService SHARED = new NeoForgeConfigService();

    @Override public void registerClient(String modId, String fileName, ModConfigSpec.Builder builder) {
        var container = ModLoadingContext.get().getActiveContainer();
        container.registerConfig(ModConfig.Type.CLIENT, builder.build(), fileName);
    }

    @Override public void registerCommon(String modId, String fileName, ModConfigSpec.Builder builder) {
        var container = ModLoadingContext.get().getActiveContainer();
        container.registerConfig(ModConfig.Type.COMMON, builder.build(), fileName);
    }

    @Override public void registerServer(String modId, String fileName, ModConfigSpec.Builder builder) {
        var container = ModLoadingContext.get().getActiveContainer();
        container.registerConfig(ModConfig.Type.SERVER, builder.build(), fileName);
    }

    private NeoForgeConfigService() {}
}
