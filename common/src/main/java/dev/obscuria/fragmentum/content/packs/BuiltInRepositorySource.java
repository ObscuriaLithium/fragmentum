package dev.obscuria.fragmentum.content.packs;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.RepositorySource;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.nio.file.*;
import java.util.Optional;
import java.util.function.Consumer;

public record BuiltInRepositorySource(PackType type) implements RepositorySource {

    @Override
    public void loadPacks(Consumer<Pack> consumer) {
        BuiltInPackRegistry.forEachRegistration(type, (modId, registration) -> {
            final @Nullable var rootPath = resolvePath(modId, registration);
            if (rootPath == null) return;
            final var packId = "%s/%s".formatted(modId, registration.directory());
            final var locationInfo = new PackLocationInfo(packId, registration.displayName(), registration.source(), Optional.empty());
            final var resourceSupplier = new PathPackResources.PathResourcesSupplier(rootPath);
            final @Nullable var pack = Pack.readMetaAndCreate(locationInfo, resourceSupplier, type, registration.config());
            if (pack != null) consumer.accept(pack);
        });
    }

    private @Nullable Path resolvePath(String modId, BuiltInPackRegistry.Registration registration) {
        return resolveRootPath(registration.modClass(), modId, registration.directory()).orElse(null);
    }

    public Optional<Path> resolveRootPath(Class<?> modClass, String modId, String directory) {
        try {
            final @Nullable var resource = modClass.getResource("/" + directory);
            if (resource == null) throw new FileNotFoundException("Resource not found: " + directory);
            final var uri = resource.toURI();
            final var scheme = uri.getScheme();

            if (scheme.equals("jar")) {
                final var fileSystem = FileSystems.getFileSystem(uri);
                return Optional.of(fileSystem.getPath("/" + directory));
            }

            if (scheme.equals("file") || scheme.equals("union")) {
                return Optional.of(Paths.get(uri));
            }

            throw new IllegalStateException("Unsupported URI scheme: " + scheme);

        } catch (Exception exception) {
            Fragmentum.LOGGER.error("Failed to resolve `{}:{}`: {}", modId, directory, exception);
            return Optional.empty();
        }
    }
}
