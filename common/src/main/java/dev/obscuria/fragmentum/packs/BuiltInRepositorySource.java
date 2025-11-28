package dev.obscuria.fragmentum.packs;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.RepositorySource;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.function.Consumer;

public record BuiltInRepositorySource(PackType type) implements RepositorySource {

    @Override
    public void loadPacks(Consumer<Pack> consumer) {
        BuiltInPackRegistry.forEachRegistration(type, (modId, registration) -> {
            final var rootPath = resolvePath(modId, registration);
            final var packId = "%s/%s".formatted(modId, registration.directory());
            final @Nullable var pack = Pack.readMetaAndCreate(
                    packId, registration.displayName(), registration.config().required(),
                    id -> new PathPackResources(packId, rootPath, true), type,
                    registration.config().defaultPosition(), registration.source());
            if (pack != null) consumer.accept(pack);
        });
    }

    private Path resolvePath(String modId, BuiltInPackRegistry.Registration registration) {
        return Fragmentum.SERVICES.resolveRootPath(modId).orElseThrow().resolve(registration.directory());
    }
}
