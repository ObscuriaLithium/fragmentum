package dev.obscuria.fragmentum.v2.core.common.packs;

import dev.obscuria.fragmentum.v2.api.common.packs.BuiltInPackBuilder;
import dev.obscuria.fragmentum.common.packs.BuiltInPackRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;

public final class _BuiltInPackBuilder implements BuiltInPackBuilder {

    private final PackType type;
    private final String directory;
    private Component displayName = Component.literal("Custom Pack");
    private PackSelectionConfig config = new PackSelectionConfig(false, Pack.Position.TOP, false);
    private PackSource source = PackSource.FEATURE;

    public static BuiltInPackBuilder resourcePack(String directory) {
        return new _BuiltInPackBuilder(PackType.CLIENT_RESOURCES, directory);
    }

    public static BuiltInPackBuilder dataPack(String directory) {
        return new _BuiltInPackBuilder(PackType.SERVER_DATA, directory);
    }

    private _BuiltInPackBuilder(PackType type, String directory) {
        this.type = type;
        this.directory = directory;
    }

    @Override
    public BuiltInPackBuilder displayName(Component displayName) {
        this.displayName = displayName;
        return this;
    }

    @Override
    public BuiltInPackBuilder selectionConfig(PackSelectionConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public BuiltInPackBuilder packSource(PackSource source) {
        this.source = source;
        return this;
    }

    @Override
    public void register(Class<?> modClass, String modId) {
        if (type == PackType.CLIENT_RESOURCES) {
            BuiltInPackRegistry.registerResourcePack(modClass, modId, directory, displayName, config, source);
        } else {
            BuiltInPackRegistry.registerDataPack(modClass, modId, directory, displayName, config, source);
        }
    }
}
