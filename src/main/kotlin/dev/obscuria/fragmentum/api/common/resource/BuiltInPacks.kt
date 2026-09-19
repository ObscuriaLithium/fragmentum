package dev.obscuria.fragmentum.api.common.resource

import dev.obscuria.fragmentum.common.resource.BuiltInPackBuilder
import net.minecraft.network.chat.Component
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.repository.PackSource

object BuiltInPacks {

	fun createClientResources(): Builder {
		return BuiltInPackBuilder(PackType.CLIENT_RESOURCES)
	}

	fun createServerData(): Builder {
		return BuiltInPackBuilder(PackType.SERVER_DATA)
	}

	interface Builder {

		fun directory(path: String): Builder

		fun resourcesFrom(modClass: Class<*>, modId: String): Builder

		fun displayName(component: Component): Builder

		fun selectionConfig(config: SelectionConfig): Builder

		fun packSource(source: PackSource): Builder

		fun build()
	}
}
