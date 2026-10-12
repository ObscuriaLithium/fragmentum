package dev.obscuria.fragmentum.mixin.client;

import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.obscuria.fragmentum.registry.AssetRegistryResourceManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ResourceLoadStateTracker;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ResourceLoadStateTracker.class)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public abstract class MixinResourceLoadStateTracker {

	@Inject(method = "startReload", at = @At("HEAD"))
	private void startReload$onHead(
			ResourceLoadStateTracker.ReloadReason reason,
			List<PackResources> resources, CallbackInfo ci
	) {
		if (reason != ResourceLoadStateTracker.ReloadReason.INITIAL) return;
		if (!(Minecraft.getInstance().getResourceManager() instanceof ReloadableResourceManager manager)) return;
		manager.registerReloadListener(AssetRegistryResourceManager.INSTANCE);
	}
}
