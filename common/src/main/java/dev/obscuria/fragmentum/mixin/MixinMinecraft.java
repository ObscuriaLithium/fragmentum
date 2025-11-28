package dev.obscuria.fragmentum.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.obscuria.fragmentum.content.packs.BuiltInRepositorySource;
import dev.obscuria.fragmentum.content.packs.FragmentumLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.RepositorySource;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = Minecraft.class, priority = 9999)
public abstract class MixinMinecraft {

    @ModifyArg(method = "<init>", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"))
    private RepositorySource[] injectFragmentumLayer(RepositorySource[] sources, @Local(argsOnly = true) GameConfig config) {
        return ArrayUtils.addAll(sources,
                new BuiltInRepositorySource(PackType.CLIENT_RESOURCES),
                FragmentumLayer.Source.create(PackType.CLIENT_RESOURCES));
    }
}
