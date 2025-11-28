package dev.obscuria.fragmentum.content.packs;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.RepositorySource;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;

public record BuiltInRepositorySource(PackType type) implements RepositorySource {

    @Override
    public void loadPacks(Consumer<Pack> consumer) {
        BuiltInPackRegistry.forEachRegistration(type, (modId, registration) -> {
            final var rootPath = resolvePath(modId, registration);
            final var packId = "%s/%s".formatted(modId, registration.directory());
            final var locationInfo = new PackLocationInfo(packId, registration.displayName(), registration.source(), Optional.empty());
            final var resourceSupplier = new PathPackResources.PathResourcesSupplier(rootPath);
            final @Nullable var pack = Pack.readMetaAndCreate(locationInfo, resourceSupplier, type, registration.config());
            if (pack != null) consumer.accept(pack);
        });
    }

    private Path resolvePath(String modId, BuiltInPackRegistry.Registration registration) {
        return Fragmentum.SERVICES.resolveRootPath(modId).orElseThrow().resolve(registration.directory());
    }
}
