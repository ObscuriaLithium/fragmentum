package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.service.ConfigService;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class FabricConfigService implements ConfigService {

    public static final FabricConfigService SHARED = new FabricConfigService();

    @Override public void registerClient(String modId, String fileName, ModConfigSpec.Builder builder) {
        ConfigRegistry.INSTANCE.register(modId, ModConfig.Type.CLIENT, builder.build(), fileName);
    }

    @Override public void registerCommon(String modId, String fileName, ModConfigSpec.Builder builder) {
        ConfigRegistry.INSTANCE.register(modId, ModConfig.Type.COMMON, builder.build(), fileName);
    }

    @Override public void registerServer(String modId, String fileName, ModConfigSpec.Builder builder) {
        ConfigRegistry.INSTANCE.register(modId, ModConfig.Type.SERVER, builder.build(), fileName);
    }

    private FabricConfigService() {}
}
