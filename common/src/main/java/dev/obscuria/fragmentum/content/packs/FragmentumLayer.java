package dev.obscuria.fragmentum.content.packs;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.*;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.world.flag.FeatureFlagSet;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public final class FragmentumLayer {

    public record Source(Path directory, PackType type) implements RepositorySource {

        public static final PackLocationInfo INFO;
        public static final Pack.Metadata CLIENT_METADATA;
        public static final Pack.Metadata SERVER_METADATA;
        public static final PackSelectionConfig CONFIG;

        public static Source create(PackType type) {
            final var userDir = Path.of(System.getProperty("user.dir")).toAbsolutePath().normalize();
            return new Source(userDir.resolve("config/" + Fragmentum.MOD_ID), type);
        }

        @Override
        public void loadPacks(Consumer<Pack> consumer) {
            final var metadata = type == PackType.CLIENT_RESOURCES ? CLIENT_METADATA : SERVER_METADATA;
            final var resourceSupplier = new ResourcesSupplier(directory);
            consumer.accept(new Pack(INFO, resourceSupplier, metadata, CONFIG));
        }

        static {
            CONFIG = new PackSelectionConfig(true, Pack.Position.TOP, false);
            INFO = new PackLocationInfo("generated/fragmentum_layer", Component.literal("Fragmentum Layer"), PackSource.BUILT_IN, Optional.empty());
            CLIENT_METADATA = new Pack.Metadata(Component.literal("Global resources"), PackCompatibility.COMPATIBLE, FeatureFlagSet.of(), List.of());
            SERVER_METADATA = new Pack.Metadata(Component.literal("Global configurations"), PackCompatibility.COMPATIBLE, FeatureFlagSet.of(), List.of());
        }
    }

    public static final class Resources extends PathPackResources {

        public Resources(PackLocationInfo info, Path root) {
            super(info, root);
        }

        @Override
        public @Nullable IoSupplier<InputStream> getRootResource(String... path) {
            final var fileName = String.join("/", path);
            if (!fileName.equals("pack.png")) return super.getRootResource(path);
            return this::getDefaultIcon;
        }

        private InputStream getDefaultIcon() {
            try {
                final @Nullable var resource = Fragmentum.class.getResourceAsStream("/fragmentum.png");
                if (resource == null) throw new IllegalStateException("Resource `fragmentum.png` not found");
                return resource;
            } catch (Exception exception) {
                Fragmentum.LOGGER.error("Failed to load Fragmentum icon", exception);
                return InputStream.nullInputStream();
            }
        }
    }

    public record ResourcesSupplier(Path root) implements Pack.ResourcesSupplier {

        @Override
        public PackResources openPrimary(PackLocationInfo info) {
            return new Resources(info, root);
        }

        @Override
        public PackResources openFull(PackLocationInfo info, Pack.Metadata metadata) {
            return new Resources(info, root);
        }
    }
}
