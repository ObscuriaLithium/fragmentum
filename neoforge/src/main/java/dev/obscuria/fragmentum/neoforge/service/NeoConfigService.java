package dev.obscuria.fragmentum.neoforge.service;

import dev.obscuria.fragmentum.Fragmentum;
import dev.obscuria.fragmentum.config.ConfigBuilder;
import dev.obscuria.fragmentum.service.ConfigService;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public final class NeoConfigService implements ConfigService {

    public static final NeoConfigService INSTANCE = new NeoConfigService();

    private NeoConfigService() {}

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
        final var container = ModLoadingContext.get().getActiveContainer();
        container.registerConfig(type, spec);
        if (Fragmentum.PLATFORM.isDedicatedServer()) return;
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
