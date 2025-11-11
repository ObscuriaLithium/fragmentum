package dev.obscuria.fragmentum.mixin;

import dev.obscuria.fragmentum.resource.FragmentumLayerSource;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.RepositorySource;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CreateWorldScreen.class)
public abstract class MixinCreateWorldScreen {

    @ModifyArg(method = "openFresh", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"))
    private static RepositorySource[] injectFragmentumLayer(RepositorySource[] sources) {
        return ArrayUtils.add(sources, FragmentumLayerSource.create(PackType.SERVER_DATA));
    }
}
