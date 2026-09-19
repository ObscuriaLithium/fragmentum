package dev.obscuria.fragmentum.api.registry

object FragmentumRegistry {

	fun registrar(modId: String): Registrar {
		//? fabric
		//return dev.obscuria.fragmentum.platform.fabric.FabricRegistrar(modId)
		//? forge
		return dev.obscuria.fragmentum.platform.forge.ForgeRegistrar(modId)
		//? neoforge
		//return dev.obscuria.fragmentum.platform.neoforge.NeoforgeRegistrar(modId);
	}
}
