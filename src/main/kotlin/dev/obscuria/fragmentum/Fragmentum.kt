package dev.obscuria.fragmentum

import net.minecraft.resources.ResourceLocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Fragmentum {

	const val MOD_ID: String = /*$ mod_id*/ "fragmentum";
	const val MOD_VERSION: String = /*$ mod_version*/ "5.1.1";
	const val MOD_FRIENDLY_NAME: String = /*$ mod_name*/ "Fragmentum";
	val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

	//? fabric
	//val PLATFORM: Platform = dev.obscuria.fragmentum.platform.fabric.FabricPlatform
	//? neoforge
	//val PLATFORM: Platform = dev.obscuria.fragmentum.platform.neoforge.NeoforgePlatform
	//? forge
	val PLATFORM: Platform = dev.obscuria.fragmentum.platform.forge.ForgePlatform

	fun id(path: String): ResourceLocation {
		//? >1.20.1
		//return ResourceLocation.fromNamespaceAndPath(MOD_ID, path)
		//? <=1.20.1
		return ResourceLocation(MOD_ID, path)
	}

	fun id(namespace: String, path: String): ResourceLocation {
		//? >1.20.1
		//return ResourceLocation.fromNamespaceAndPath(namespace, path)
		//? <=1.20.1
		return ResourceLocation(namespace, path)
	}

	fun parseId(id: String): ResourceLocation {
		//? >1.20.1
		//return ResourceLocation.parse(id)
		//? <=1.20.1
		return ResourceLocation(id)
	}

	fun onInitialize() {}
}
