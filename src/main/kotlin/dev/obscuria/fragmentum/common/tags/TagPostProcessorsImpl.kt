package dev.obscuria.fragmentum.common.tags

import dev.obscuria.fragmentum.Fragmentum
import dev.obscuria.fragmentum.api.common.tags.DynamicTagEntry
import dev.obscuria.fragmentum.api.common.tags.TagPostProcessor
import dev.obscuria.fragmentum.api.config.Configurable
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.tags.TagLoader
import org.jetbrains.annotations.ApiStatus
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

@ApiStatus.Internal
internal object TagPostProcessorsImpl {

	private const val SOURCE: String = "fragmentum:postprocessor"
	private val POSTPROCESSORS = ConcurrentHashMap<TagKey<*>, MutableList<TagPostProcessor>>()
	private val IRREGULAR_DIRECTORIES: Map<ResourceLocation, String> = mapOf(
		Fragmentum.parseId("block") to "tags/blocks",
		Fragmentum.parseId("item") to "tags/items",
		Fragmentum.parseId("entity_type") to "tags/entity_types",
		Fragmentum.parseId("fluid") to "tags/fluids",
		Fragmentum.parseId("game_event") to "tags/game_events"
	)

	fun register(tagKey: TagKey<*>, postProcessor: TagPostProcessor) {
		POSTPROCESSORS.getOrPut(tagKey) { CopyOnWriteArrayList() }.add(postProcessor)
	}

	fun register(tagKey: TagKey<*>, entries: Collection<DynamicTagEntry>) {
		register(tagKey) { entries }
	}

	fun register(tagKey: TagKey<*>, configValue: Configurable<List<String>>) {
		register(tagKey) { configValue.get().map(DynamicTagEntry::parse) }
	}

	fun directoryFor(registryKey: ResourceKey<out Registry<*>>): String {
		return "tags/${IRREGULAR_DIRECTORIES[registryKey.location()] ?: registryKey.location().path}"
	}

	fun build(directory: String, builders: MutableMap<ResourceLocation, MutableList<TagLoader.EntryWithSource>>) {

		for ((tagKey, postProcessors) in POSTPROCESSORS) {
			val expectedDirectory = directoryFor(tagKey.registry())
			if (directory != expectedDirectory) continue

			val entries = builders.computeIfAbsent(tagKey.location()) { ArrayList() }
			for (postprocessor in postProcessors) runCatching {
				for (entry in postprocessor.collect()) {
					entries.add(TagLoader.EntryWithSource(entry.resolve(), SOURCE))
				}
			}
		}
	}
}
