package dev.obscuria.fragmentum.content.packs;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.PackSource;

import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public interface BuiltInPackRegistry {

    Multimap<String, Registration> CLIENT_REGISTRATIONS = HashMultimap.create();
    Multimap<String, Registration> SERVER_REGISTRATIONS = HashMultimap.create();

    static void registerResourcePack(String modId, String directory, Component displayName, PackSelectionConfig config, PackSource source) {
        CLIENT_REGISTRATIONS.put(modId, new Registration(directory, displayName, config, source));
    }

    static void registerDataPack(String modId, String directory, Component displayName, PackSelectionConfig config, PackSource source) {
        SERVER_REGISTRATIONS.put(modId, new Registration(directory, displayName, config, source));
    }

    static void forEachRegistration(PackType type, BiConsumer<String, Registration> consumer) {
        if (type == PackType.CLIENT_RESOURCES) {
            CLIENT_REGISTRATIONS.forEach(consumer);
        } else {
            SERVER_REGISTRATIONS.forEach(consumer);
        }
    }

    record Registration(String directory, Component displayName, PackSelectionConfig config, PackSource source) {}
}
