package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.config.AnnotationConfigBuilder;
import dev.obscuria.fragmentum.config.ConfigBuilder;
import dev.obscuria.fragmentum.config.ConfigLayout;
import dev.obscuria.fragmentum.service.ConfigService;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import fuzs.forgeconfigapiport.api.config.v2.ModConfigEvents;
import net.minecraftforge.fml.config.ModConfig;

import java.util.function.Consumer;

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
            ForgeConfigRegistry.INSTANCE.register(modId, type, spec);
        } else {
            ForgeConfigRegistry.INSTANCE.register(modId, type, spec, builder.fileName);
        }
    }

    @Override
    public <T extends ConfigLayout> void registerClient(String modId, T layout, Consumer<T> listener) {
        final var instance = AnnotationConfigBuilder.build(layout);
        ForgeConfigRegistry.INSTANCE.register(modId, ModConfig.Type.CLIENT, instance.spec());
        ModConfigEvents.loading(modId).register(config -> instance.maybeUpdate(config, layout, listener));
        ModConfigEvents.reloading(modId).register(config -> instance.maybeUpdate(config, layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerClient(String modId, String fileName, T layout, Consumer<T> listener) {
        final var instance = AnnotationConfigBuilder.build(layout);
        ForgeConfigRegistry.INSTANCE.register(modId, ModConfig.Type.CLIENT, instance.spec(), fileName);
        ModConfigEvents.loading(modId).register(config -> instance.maybeUpdate(config, layout, listener));
        ModConfigEvents.reloading(modId).register(config -> instance.maybeUpdate(config, layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerCommon(String modId, T layout, Consumer<T> listener) {
        final var instance = AnnotationConfigBuilder.build(layout);
        ForgeConfigRegistry.INSTANCE.register(modId, ModConfig.Type.COMMON, instance.spec());
        ModConfigEvents.loading(modId).register(config -> instance.maybeUpdate(config, layout, listener));
        ModConfigEvents.reloading(modId).register(config -> instance.maybeUpdate(config, layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerCommon(String modId, String fileName, T layout, Consumer<T> listener) {
        final var instance = AnnotationConfigBuilder.build(layout);
        ForgeConfigRegistry.INSTANCE.register(modId, ModConfig.Type.COMMON, instance.spec(), fileName);
        ModConfigEvents.loading(modId).register(config -> instance.maybeUpdate(config, layout, listener));
        ModConfigEvents.reloading(modId).register(config -> instance.maybeUpdate(config, layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerServer(String modId, T layout, Consumer<T> listener) {
        final var instance = AnnotationConfigBuilder.build(layout);
        ForgeConfigRegistry.INSTANCE.register(modId, ModConfig.Type.SERVER, instance.spec());
        ModConfigEvents.loading(modId).register(config -> instance.maybeUpdate(config, layout, listener));
        ModConfigEvents.reloading(modId).register(config -> instance.maybeUpdate(config, layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerServer(String modId, String fileName, T layout, Consumer<T> listener) {
        final var instance = AnnotationConfigBuilder.build(layout);
        ForgeConfigRegistry.INSTANCE.register(modId, ModConfig.Type.SERVER, instance.spec(), fileName);
        ModConfigEvents.loading(modId).register(config -> instance.maybeUpdate(config, layout, listener));
        ModConfigEvents.reloading(modId).register(config -> instance.maybeUpdate(config, layout, listener));
    }
}
