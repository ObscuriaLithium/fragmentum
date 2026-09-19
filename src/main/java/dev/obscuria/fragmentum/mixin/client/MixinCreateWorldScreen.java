package dev.obscuria.fragmentum.mixin.client;

import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.obscuria.fragmentum.common.resource.BuiltInRepositorySource;
import dev.obscuria.fragmentum.common.resource.FragmentumLayer;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.RepositorySource;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = CreateWorldScreen.class, priority = 9999)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public abstract class MixinCreateWorldScreen {

    @ModifyArg(method = "openFresh", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"))
    private static RepositorySource[] openFresh$addPackSource(RepositorySource[] sources) {
        return ArrayUtils.addAll(sources,
				new BuiltInRepositorySource(PackType.SERVER_DATA),
				FragmentumLayer.INSTANCE.createSource(PackType.SERVER_DATA));
    }
}
