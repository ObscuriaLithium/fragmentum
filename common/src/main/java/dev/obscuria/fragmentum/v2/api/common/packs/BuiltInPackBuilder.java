package dev.obscuria.fragmentum.v2.api.common.packs;

import dev.obscuria.fragmentum.v2.core.common.packs._BuiltInPackBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.repository.PackSource;

@SuppressWarnings("unused")
public interface BuiltInPackBuilder {

    static BuiltInPackBuilder resourcePack(String directory) {
        return _BuiltInPackBuilder.resourcePack(directory);
    }

    static BuiltInPackBuilder dataPack(String directory) {
        return _BuiltInPackBuilder.dataPack(directory);
    }

    BuiltInPackBuilder displayName(Component displayName);

    BuiltInPackBuilder selectionConfig(PackSelectionConfig config);

    BuiltInPackBuilder packSource(PackSource source);

    void register(Class<?> modClass, String modId);
}
