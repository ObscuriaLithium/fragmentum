package dev.obscuria.fragmentum.api.registry

import com.mojang.serialization.Codec
import dev.obscuria.fragmentum.api.common.event.Event
import dev.obscuria.fragmentum.registry.AssetRegistryImpl
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import java.util.function.Consumer

interface AssetRegistry<T : Any> {

	val size: Int

	val entries: Set<Map.Entry<ResourceLocation, T>>

	val values: Collection<T>

	val byNameCodec: Codec<T>

	operator fun iterator(): Iterator<T>

	operator fun contains(value: T): Boolean

	operator fun contains(id: ResourceLocation): Boolean

	operator fun get(id: ResourceLocation): T?

	fun getKey(value: T): ResourceLocation?

	fun getOrThrow(id: ResourceLocation): T

	fun getKeyOrThrow(value: T): ResourceLocation

	companion object {

		val ON_RELOAD_START = Event.create<Consumer<ResourceManager>>()
		val ON_RELOAD_END = Event.create<Consumer<ResourceManager>>()

		fun <T : Any> create(
			id: ResourceLocation,
			directory: String,
			codec: Codec<T>
		): AssetRegistry<T> {
			return AssetRegistryImpl(id, directory, codec)
		}
	}
}
