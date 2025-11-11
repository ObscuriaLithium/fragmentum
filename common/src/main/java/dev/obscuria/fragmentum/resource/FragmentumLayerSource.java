package dev.obscuria.fragmentum.resource;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;

public record FragmentumLayerSource(Path directory, PackType type) implements RepositorySource {

    public static FragmentumLayerSource create(PackType type) {
        final var root = Path.of(System.getProperty("user.dir")).toAbsolutePath().normalize();
        return new FragmentumLayerSource(layerDirectory(root), type);
    }

    public static Path layerDirectory(Path root) {
        return root.resolve("config/" + Fragmentum.MOD_ID);
    }

    @Override
    public void loadPacks(Consumer<Pack> consumer) {
        Optional.ofNullable(Pack.readMetaAndCreate(
                Fragmentum.MOD_ID, Component.literal("Fragmentum Layer"),
                true, packId -> new FragmentumLayerResources(directory, type),
                type, Pack.Position.TOP, PackSource.BUILT_IN)
        ).ifPresent(consumer);
    }
}
