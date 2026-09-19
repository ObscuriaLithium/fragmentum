package dev.obscuria.fragmentum.common.resource

import dev.obscuria.fragmentum.api.common.resource.BuiltInPacks
import dev.obscuria.fragmentum.api.common.resource.SelectionConfig
import net.minecraft.network.chat.Component
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.repository.Pack
import net.minecraft.server.packs.repository.PackSource
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class BuiltInPackBuilder(
	val type: PackType
) : BuiltInPacks.Builder {

	private var directory: String? = null
	private var modClass: Class<*>? = null
	private var modId: String? = null
	private var displayName: Component = Component.literal("Custom Pack")
	private var config: SelectionConfig = SelectionConfig(false, Pack.Position.TOP, false)
	private var source: PackSource = PackSource.FEATURE

	override fun directory(path: String): BuiltInPacks.Builder {
		this.directory = path
		return this
	}

	override fun resourcesFrom(modClass: Class<*>, modId: String): BuiltInPacks.Builder {
		this.modClass = modClass
		this.modId = modId
		return this
	}

	override fun displayName(component: Component): BuiltInPacks.Builder {
		this.displayName = component
		return this
	}

	override fun selectionConfig(config: SelectionConfig): BuiltInPacks.Builder {
		this.config = config
		return this
	}

	override fun packSource(source: PackSource): BuiltInPacks.Builder {
		this.source = source
		return this
	}

	override fun build() {
		val directory = this.directory ?: throw IllegalStateException("Directory cannot be null")
		val modClass = this.modClass ?: throw IllegalStateException("Mod class cannot be null")
		val modId = this.modId ?: throw IllegalStateException("Mod ID cannot be null")
		if (type == PackType.CLIENT_RESOURCES) {
			BuiltInPackRegistry.registerClientResources(modClass, modId, directory, displayName, config, source)
		} else {
			BuiltInPackRegistry.registerServerData(modClass, modId, directory, displayName, config, source)
		}
	}
}
