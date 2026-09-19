package dev.obscuria.fragmentum.mixin;

import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.obscuria.fragmentum.common.tags.TagPostProcessorsImpl;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

@Mixin(value = TagLoader.class, priority = 0)
@MixinEnvironment(type = MixinEnvironment.Env.DEFAULT)
public abstract class MixinTagLoader {

	private @Final @Shadow String directory;

	@SuppressWarnings("rawtypes")
	@Inject(method = "build(Ljava/util/Map;)Ljava/util/Map;", at = @At("HEAD"))
	private void build$onHead(
			Map<ResourceLocation, List<TagLoader.EntryWithSource>> builders,
			CallbackInfoReturnable<Map> cir
	) {
		TagPostProcessorsImpl.INSTANCE.build(directory, builders);
	}
}
