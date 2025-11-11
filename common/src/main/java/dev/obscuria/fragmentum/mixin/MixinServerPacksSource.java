package dev.obscuria.fragmentum.mixin;

import dev.obscuria.fragmentum.resource.FragmentumLayerSource;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.server.packs.repository.ServerPacksSource;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ServerPacksSource.class)
public abstract class MixinServerPacksSource {

    @ModifyArg(method = "createPackRepository(Ljava/nio/file/Path;)Lnet/minecraft/server/packs/repository/PackRepository;", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"))
    private static RepositorySource[] injectFragmentumLayer(RepositorySource[] sources) {
        return ArrayUtils.add(sources, FragmentumLayerSource.create(PackType.SERVER_DATA));
    }
}
