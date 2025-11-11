package dev.obscuria.fragmentum.resource;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.BuiltInMetadata;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.nio.file.Path;

public class FragmentumLayerResources extends PathPackResources {

    private static final ResourceLocation ICON = Fragmentum.key("fragmentum_layer.png");
    private static final Component DESCRIPTION = Component.translatable("text.fragmentum.global_config");
    private final BuiltInMetadata metadata;

    public FragmentumLayerResources(Path root, PackType type) {
        super(Fragmentum.MOD_ID, root, true);
        final var version = SharedConstants.getCurrentVersion().getPackVersion(type);
        this.metadata = BuiltInMetadata.of(PackMetadataSection.TYPE, new PackMetadataSection(DESCRIPTION, version));
    }

    @Override
    public <T> T getMetadataSection(MetadataSectionSerializer<T> deserializer) {
        return metadata.get(deserializer);
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
