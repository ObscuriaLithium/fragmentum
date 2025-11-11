package dev.obscuria.fragmentum.forge.service;

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
    public <T extends ConfigLayout> void registerClient(String modId, T layout, Consumer<T> listener) {
        final var instance = ConfigBuilder.build(layout);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, instance.spec());
        ForgeFragmentum.addListener(modId, (final ModConfigEvent event) -> instance.maybeUpdate(event.getConfig(), layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerClient(String modId, String fileName, T layout, Consumer<T> listener) {
        final var instance = ConfigBuilder.build(layout);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, instance.spec(), fileName);
        ForgeFragmentum.addListener(modId, (final ModConfigEvent event) -> instance.maybeUpdate(event.getConfig(), layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerCommon(String modId, T layout, Consumer<T> listener) {
        final var instance = ConfigBuilder.build(layout);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, instance.spec());
        ForgeFragmentum.addListener(modId, (final ModConfigEvent event) -> instance.maybeUpdate(event.getConfig(), layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerCommon(String modId, String fileName, T layout, Consumer<T> listener) {
        final var instance = ConfigBuilder.build(layout);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, instance.spec(), fileName);
        ForgeFragmentum.addListener(modId, (final ModConfigEvent event) -> instance.maybeUpdate(event.getConfig(), layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerServer(String modId, T layout, Consumer<T> listener) {
        final var instance = ConfigBuilder.build(layout);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, instance.spec());
        ForgeFragmentum.addListener(modId, (final ModConfigEvent event) -> instance.maybeUpdate(event.getConfig(), layout, listener));
    }

    @Override
    public <T extends ConfigLayout> void registerServer(String modId, String fileName, T layout, Consumer<T> listener) {
        final var instance = ConfigBuilder.build(layout);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, instance.spec(), fileName);
        ForgeFragmentum.addListener(modId, (final ModConfigEvent event) -> instance.maybeUpdate(event.getConfig(), layout, listener));
    }
}
