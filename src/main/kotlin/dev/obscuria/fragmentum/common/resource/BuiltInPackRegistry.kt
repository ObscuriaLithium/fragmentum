package dev.obscuria.fragmentum.common.resource

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import dev.obscuria.fragmentum.api.common.resource.SelectionConfig
import net.minecraft.network.chat.Component
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.repository.PackSource
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal object BuiltInPackRegistry {

	val CLIENT_REGISTRATIONS: Multimap<String, Registration> = HashMultimap.create<String, Registration>()
	val SERVER_REGISTRATIONS: Multimap<String, Registration> = HashMultimap.create<String, Registration>()

	fun registerClientResources(
		modClass: Class<*>,
		modId: String,
		directory: String,
		displayName: Component,
		config: SelectionConfig,
		source: PackSource
	) {
		CLIENT_REGISTRATIONS.put(modId, Registration(modClass, directory, displayName, config, source))
	}

	fun registerServerData(
		modClass: Class<*>,
		modId: String,
		directory: String,
		displayName: Component,
		config: SelectionConfig,
		source: PackSource
	) {
		SERVER_REGISTRATIONS.put(modId, Registration(modClass, directory, displayName, config, source))
	}

	fun forEachRegistration(type: PackType, consumer: (String, Registration) -> Unit) {
		if (type == PackType.CLIENT_RESOURCES) {
			CLIENT_REGISTRATIONS.forEach(consumer)
		} else {
			SERVER_REGISTRATIONS.forEach(consumer)
		}
	}

	data class Registration(
		val modClass: Class<*>,
		val directory: String,
		val displayName: Component,
		val config: SelectionConfig,
		val source: PackSource
	)
}
