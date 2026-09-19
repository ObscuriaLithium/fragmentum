package dev.obscuria.fragmentum.api.registry

import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

fun interface BootstrapContext<T> where T : Any {

	fun register(name: String, value: () -> T)

	companion object {

		fun <T> create(
            registrar: Registrar,
            registryKey: ResourceKey<out Registry<T>>,
            idResolver: (String) -> ResourceLocation
		): BootstrapContext<T> where T : Any {
			return BootstrapContext { name, value ->
				registrar.register(registryKey, idResolver(name), value)
			}
		}
	}
}
