package dev.obscuria.fragmentum.mixin;

import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.obscuria.fragmentum.common.resource.BuiltInRepositorySource;
import dev.obscuria.fragmentum.common.resource.FragmentumLayer;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.server.packs.repository.ServerPacksSource;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ServerPacksSource.class)
@MixinEnvironment(type = MixinEnvironment.Env.DEFAULT)
public abstract class MixinServerPacksSource {

	//? 1.20.1 {
	@ModifyArg(method = "createPackRepository(Ljava/nio/file/Path;)Lnet/minecraft/server/packs/repository/PackRepository;", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"))
	private static RepositorySource[] createPackRepository$addPackSource(RepositorySource[] sources) {
		return ArrayUtils.addAll(sources,
				new BuiltInRepositorySource(PackType.SERVER_DATA),
				FragmentumLayer.INSTANCE.createSource(PackType.SERVER_DATA));
	}
	//?}

	//? >=1.21.1 {
    /*@ModifyArg(method = "createPackRepository(Ljava/nio/file/Path;Lnet/minecraft/world/level/validation/DirectoryValidator;)Lnet/minecraft/server/packs/repository/PackRepository;", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"))
    private static RepositorySource[] createPackRepository$addPackSource(RepositorySource[] sources) {
        return ArrayUtils.addAll(sources,
				new BuiltInRepositorySource(PackType.SERVER_DATA),
				FragmentumLayer.INSTANCE.createSource(PackType.SERVER_DATA));
    }
	*///?}
}
