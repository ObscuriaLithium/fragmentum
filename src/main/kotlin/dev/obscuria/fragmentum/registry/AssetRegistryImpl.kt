package dev.obscuria.fragmentum.registry

import com.google.gson.JsonParser
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.JsonOps
import dev.obscuria.fragmentum.Fragmentum
import dev.obscuria.fragmentum.api.registry.AssetRegistry
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.Resource
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class AssetRegistryImpl<T : Any>(
	val registryId: ResourceLocation,
	val directory: String,
	val codec: Codec<T>
) : AssetRegistry<T> {

	val keyToElement = mutableMapOf<ResourceLocation, T>()
	val elementToKey = mutableMapOf<T, ResourceLocation>()

	init {
		REGISTRIES[registryId] = this
	}

	override val size: Int
		get() = keyToElement.size

	override val entries: Set<Map.Entry<ResourceLocation, T>>
		get() = keyToElement.entries

	override val values: Collection<T>
		get() = keyToElement.values

	override val byNameCodec: Codec<T> by lazy {
		ResourceLocation.CODEC.flatXmap(this::tryGetValue, this::tryGetKey)
	}

	override fun iterator(): Iterator<T> {
		return values.iterator()
	}

	override fun contains(value: T): Boolean {
		return elementToKey.contains(value)
	}

	override fun contains(id: ResourceLocation): Boolean {
		return keyToElement.contains(id)
	}

	override fun get(id: ResourceLocation): T? {
		return keyToElement[id]
	}

	override fun getKey(value: T): ResourceLocation? {
		return elementToKey[value]
	}

	override fun getOrThrow(id: ResourceLocation): T {
		return keyToElement[id] ?: throw NoSuchElementException("No element with id $id")
	}

	override fun getKeyOrThrow(value: T): ResourceLocation {
		return elementToKey[value] ?: throw NoSuchElementException("No key for value $value")
	}

	internal fun onReloadStart() {
		keyToElement.clear()
		elementToKey.clear()
	}

	internal fun load(path: ResourceLocation, resource: Resource) {
		try {
			val id = path.withPath { it.removePrefix("$directory/").removeSuffix(".json") }
			resource.openAsReader().use { reader ->
				val json = JsonParser.parseReader(reader)
				val result = codec.decode(JsonOps.INSTANCE, json)
				//? 1.20.1
				val value = result.getOrThrow(false) {}.first
				//? 1.21.1
				//val value = result.getOrThrow().first
				register(id, value)
			}
		} catch (exception: Exception) {
			Fragmentum.LOGGER.error("Failed to load asset `$path` for AssetRegistry[$registryId]", exception)
		}
	}

	internal fun register(id: ResourceLocation, value: T) {
		keyToElement[id] = value
		elementToKey[value] = id
	}

	internal fun onReloadEnd() {}

	private fun tryGetValue(id: ResourceLocation): DataResult<T> {
		return get(id)
			?.let { DataResult.success(it) }
			?: DataResult.error { "No element with id $id" }
	}

	private fun tryGetKey(value: T): DataResult<ResourceLocation> {
		return getKey(value)
			?.let { DataResult.success(it) }
			?: DataResult.error { "No key for value $value" }
	}

	companion object {

		val REGISTRIES = mutableMapOf<ResourceLocation, AssetRegistryImpl<*>>()
	}
}
