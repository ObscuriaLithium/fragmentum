package dev.obscuria.fragmentum.service;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface ConfigService {

    void registerClient(String modId, String fileName, ModConfigSpec.Builder builder);

    void registerCommon(String modId, String fileName, ModConfigSpec.Builder builder);

    void registerServer(String modId, String fileName, ModConfigSpec.Builder builder);
}
