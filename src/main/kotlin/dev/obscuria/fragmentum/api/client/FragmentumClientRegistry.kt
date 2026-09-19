package dev.obscuria.fragmentum.api.client

object FragmentumClientRegistry {

	fun registrar(modId: String): ClientRegistrar {
		//? if fabric
		//return dev.obscuria.fragmentum.platform.fabric.FabricClientRegistrar(modId);
		//? if forge
		return dev.obscuria.fragmentum.platform.forge.ForgeClientRegistrar(modId)
		//? if neoforge
		//return dev.obscuria.fragmentum.platform.neoforge.NeoforgeClientRegistrar(modId);
	}
}
