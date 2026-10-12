package dev.obscuria.fragmentum.registry

import dev.obscuria.fragmentum.api.registry.AssetRegistry
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal object AssetRegistryResourceManager : ResourceManagerReloadListener {

	override fun onResourceManagerReload(manager: ResourceManager) {
		AssetRegistry.ON_RELOAD_START.broadcast { it.accept(manager) }
		AssetRegistryImpl.REGISTRIES.values.forEach {
			it.onReloadStart()
			manager.listResources(it.directory, ::isValidResource).forEach(it::load)
			it.onReloadEnd()
		}
		AssetRegistry.ON_RELOAD_END.broadcast { it.accept(manager) }
	}

	private fun isValidResource(resource: ResourceLocation): Boolean {
		return resource.path.endsWith(".json")
	}
}
