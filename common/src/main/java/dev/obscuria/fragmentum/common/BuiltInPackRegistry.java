package dev.obscuria.fragmentum.common;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.experimental.UtilityClass;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.PackSource;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.BiConsumer;

@UtilityClass
@ApiStatus.Internal
public final class BuiltInPackRegistry {

    private static final Multimap<String, Registration> CLIENT_REGISTRATIONS = HashMultimap.create();
    private static final Multimap<String, Registration> SERVER_REGISTRATIONS = HashMultimap.create();

    public static void registerResourcePack(
            Class<?> modClass,
            String modId,
            String directory,
            Component displayName,
            PackSelectionConfig config,
            PackSource source
    ) {
        CLIENT_REGISTRATIONS.put(modId, new Registration(modClass, directory, displayName, config, source));
    }

    public static void registerDataPack(
            Class<?> modClass,
            String modId,
            String directory,
            Component displayName,
            PackSelectionConfig config,
            PackSource source
    ) {
        SERVER_REGISTRATIONS.put(modId, new Registration(modClass, directory, displayName, config, source));
    }

    public static void forEachRegistration(PackType type, BiConsumer<String, Registration> consumer) {
        if (type == PackType.CLIENT_RESOURCES) {
            CLIENT_REGISTRATIONS.forEach(consumer);
        } else {
            SERVER_REGISTRATIONS.forEach(consumer);
        }
    }

    public record Registration(
            Class<?> modClass,
            String directory,
            Component displayName,
            PackSelectionConfig config,
            PackSource source
    ) {}
}
