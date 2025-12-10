package dev.obscuria.fragmentum.content.packs;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.repository.PackSource;
import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("unused")
public final class BuiltInPackBuilder {

    private BuiltInPackBuilder() {}

    public static BuiltInPackBuilder resourcePack(String directory) {
        throw new NotImplementedException();
    }

    public static BuiltInPackBuilder dataPack(String directory) {
        throw new NotImplementedException();
    }

    public BuiltInPackBuilder displayName(Component displayName) {
        throw new NotImplementedException();
    }

    public BuiltInPackBuilder selectionConfig(PackSelectionConfig config) {
        throw new NotImplementedException();
    }

    public BuiltInPackBuilder packSource(PackSource source) {
        throw new NotImplementedException();
    }

    public void register(Class<?> modClass, String modId) {
        throw new NotImplementedException();
    }
}
