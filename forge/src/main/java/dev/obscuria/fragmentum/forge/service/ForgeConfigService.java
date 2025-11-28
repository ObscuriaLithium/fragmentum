package dev.obscuria.fragmentum.forge.service;

import dev.obscuria.fragmentum.config.AnnotationConfigBuilder;
import dev.obscuria.fragmentum.config.ConfigBuilder;
import dev.obscuria.fragmentum.config.ConfigLayout;
import dev.obscuria.fragmentum.forge.ForgeFragmentum;
import dev.obscuria.fragmentum.service.ConfigService;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.function.Consumer;

public final class ForgeConfigService implements ConfigService {

    public static final ForgeConfigService INSTANCE = new ForgeConfigService();

    private ForgeConfigService() {}

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
            ModLoadingContext.get().registerConfig(type, spec);
        } else {
            ModLoadingContext.get().registerConfig(type, spec, builder.fileName);
        }
    }

    @Override
    public <T extends ConfigLayout> void registerClient(String modId, T layout, Consumer<T> listener) {
        final var instance = AnnotationConfigBuilder.build(layout);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, instance.spec());
        ForgeFragmentum.addListener(modId, (final ModConfigEvent event) -> instance.maybeUpdate(event.getConfig(), layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerClient(String modId, String fileName, T layout, Consumer<T> listener) {
        final var instance = AnnotationConfigBuilder.build(layout);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, instance.spec(), fileName);
        ForgeFragmentum.addListener(modId, (final ModConfigEvent event) -> instance.maybeUpdate(event.getConfig(), layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerCommon(String modId, T layout, Consumer<T> listener) {
        final var instance = AnnotationConfigBuilder.build(layout);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, instance.spec());
        ForgeFragmentum.addListener(modId, (final ModConfigEvent event) -> instance.maybeUpdate(event.getConfig(), layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerCommon(String modId, String fileName, T layout, Consumer<T> listener) {
        final var instance = AnnotationConfigBuilder.build(layout);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, instance.spec(), fileName);
        ForgeFragmentum.addListener(modId, (final ModConfigEvent event) -> instance.maybeUpdate(event.getConfig(), layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerServer(String modId, T layout, Consumer<T> listener) {
        final var instance = AnnotationConfigBuilder.build(layout);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, instance.spec());
        ForgeFragmentum.addListener(modId, (final ModConfigEvent event) -> instance.maybeUpdate(event.getConfig(), layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerServer(String modId, String fileName, T layout, Consumer<T> listener) {
        final var instance = AnnotationConfigBuilder.build(layout);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, instance.spec(), fileName);
        ForgeFragmentum.addListener(modId, (final ModConfigEvent event) -> instance.maybeUpdate(event.getConfig(), layout, listener));
    }
}
