package dev.obscuria.fragmentum.mixin.client;

import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.obscuria.fragmentum.common.resource.BuiltInRepositorySource;
import dev.obscuria.fragmentum.common.resource.FragmentumLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.RepositorySource;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = Minecraft.class, priority = 9999)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public abstract class MixinMinecraft {

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"))
    private RepositorySource[] init$addPackSource(RepositorySource[] sources) {
        return ArrayUtils.addAll(sources,
				new BuiltInRepositorySource(PackType.CLIENT_RESOURCES),
				FragmentumLayer.INSTANCE.createSource(PackType.CLIENT_RESOURCES));
    }
}
