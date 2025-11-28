package dev.obscuria.fragmentum.packs;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.world.flag.FeatureFlagSet;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.function.Consumer;

public final class FragmentumLayer {

    public record Source(Path directory, PackType type) implements RepositorySource {

        public static final Pack.Info CLIENT_METADATA;
        public static final Pack.Info SERVER_METADATA;

        public static Source create(PackType type) {
            final var root = Path.of(System.getProperty("user.dir")).toAbsolutePath().normalize();
            return new Source(root.resolve("config/" + Fragmentum.MOD_ID), type);
        }

        @Override
        public void loadPacks(Consumer<Pack> consumer) {
            final var metadata = type == PackType.CLIENT_RESOURCES ? CLIENT_METADATA : SERVER_METADATA;
            consumer.accept(Pack.create(
                    "generated/fragmentum_layer", Component.literal("Fragmentum Layer"),
                    true, packId -> new Resources(packId, directory),
                    metadata, type, Pack.Position.TOP, false, PackSource.BUILT_IN));
        }

        static {
            final var clientVersion = SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES);
            final var serverVersion = SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA);
            CLIENT_METADATA = new Pack.Info(Component.literal("Global resources"), clientVersion, FeatureFlagSet.of());
            SERVER_METADATA = new Pack.Info(Component.literal("Global configurations"), serverVersion, FeatureFlagSet.of());
        }
    }

    public static final class Resources extends PathPackResources {

        private static final ResourceLocation ICON = Fragmentum.key("fragmentum_layer.png");

        public Resources(String packId, Path root) {
            super(packId, root, true);
        }

        @Override
        public @Nullable IoSupplier<InputStream> getRootResource(String... path) {
            final var fileName = String.join("/", path);
            if (!fileName.equals("pack.png")) return super.getRootResource(path);
            final var manager = Minecraft.getInstance().getResourceManager();
            final var resource = manager.getResource(ICON);
            if (resource.isEmpty()) return null;
            return resource.orElseThrow()::open;
        }
    }
}
