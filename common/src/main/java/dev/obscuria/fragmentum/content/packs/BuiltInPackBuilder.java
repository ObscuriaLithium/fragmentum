package dev.obscuria.fragmentum.content.packs;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;

@SuppressWarnings("unused")
public final class BuiltInPackBuilder {

    private final PackType type;
    private final String directory;
    private Component displayName = Component.literal("Custom Pack");
    private PackSelectionConfig config = new PackSelectionConfig(false, Pack.Position.TOP, false);
    private PackSource source = PackSource.FEATURE;

    private BuiltInPackBuilder(PackType type, String directory) {
        this.type = type;
        this.directory = directory;
    }

    public static BuiltInPackBuilder resourcePack(String directory) {
        return new BuiltInPackBuilder(PackType.CLIENT_RESOURCES, directory);
    }

    public static BuiltInPackBuilder dataPack(String directory) {
        return new BuiltInPackBuilder(PackType.SERVER_DATA, directory);
    }

    public BuiltInPackBuilder displayName(Component displayName) {
        this.displayName = displayName;
        return this;
    }

    public BuiltInPackBuilder selectionConfig(PackSelectionConfig config) {
        this.config = config;
        return this;
    }

    public BuiltInPackBuilder packSource(PackSource source) {
        this.source = source;
        return this;
    }

    public void register(Class<?> modClass, String modId) {
        if (type == PackType.CLIENT_RESOURCES) {
            BuiltInPackRegistry.registerResourcePack(modClass, modId, directory, displayName, config, source);
        } else {
            BuiltInPackRegistry.registerDataPack(modClass, modId, directory, displayName, config, source);
        }
    }

    public void register(String modId) {
        throw new UnsupportedOperationException("Fragmentum API was changed to fix critical issues. Please update dependent mods.");
    }
}
