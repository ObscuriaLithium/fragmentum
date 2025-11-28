package dev.obscuria.fragmentum.mixin;

import dev.obscuria.fragmentum.content.packs.BuiltInRepositorySource;
import dev.obscuria.fragmentum.content.packs.FragmentumLayer;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.RepositorySource;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = CreateWorldScreen.class, priority = 9999)
public abstract class MixinCreateWorldScreen {

    @ModifyArg(method = "openFresh", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"))
    private static RepositorySource[] injectFragmentumLayer(RepositorySource[] sources) {
        return ArrayUtils.addAll(sources,
                new BuiltInRepositorySource(PackType.SERVER_DATA),
                FragmentumLayer.Source.create(PackType.SERVER_DATA));
    }
}
