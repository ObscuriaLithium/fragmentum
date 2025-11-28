package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.Fragmentum;
import dev.obscuria.fragmentum.config.ConfigBuilder;
import dev.obscuria.fragmentum.service.ConfigService;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.client.ConfigScreenFactoryRegistry;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

public final class FabricConfigService implements ConfigService {

    public static final FabricConfigService INSTANCE = new FabricConfigService();

    private FabricConfigService() {}

    @Override
    public void registerClient(String modId, ConfigBuilder builder) {
        registerInternal(modId, builder, ModConfig.Type.CLIENT);
    }

    @Override
    public void registerCommon(String modId, ConfigBuilder builder) {
        registerInternal(modId, builder, ModConfig.Type.COMMON);
    }

    @Override
    public void registerServer(String modId, ConfigBuilder builder) {
        registerInternal(modId, builder, ModConfig.Type.SERVER);
    }

    private void registerInternal(String modId, ConfigBuilder builder, ModConfig.Type type) {
        final var spec = builder.specBuilder.build();
        if (builder.fileName == null) {
            NeoForgeConfigRegistry.INSTANCE.register(modId, type, spec);
        } else {
            NeoForgeConfigRegistry.INSTANCE.register(modId, type, spec, builder.fileName);
        }
        if (Fragmentum.PLATFORM.isDedicatedServer()) return;
        ConfigScreenFactoryRegistry.INSTANCE.register(modId, ConfigurationScreen::new);
    }
}
