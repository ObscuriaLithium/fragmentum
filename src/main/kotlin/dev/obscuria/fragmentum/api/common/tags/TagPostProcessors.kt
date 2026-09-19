package dev.obscuria.fragmentum.api.common.tags

import dev.obscuria.fragmentum.api.config.Configurable
import dev.obscuria.fragmentum.common.tags.TagPostProcessorsImpl
import net.minecraft.tags.TagKey

object TagPostProcessors {

	fun register(tagKey: TagKey<*>, postProcessor: TagPostProcessor) {
		TagPostProcessorsImpl.register(tagKey, postProcessor)
	}

	fun register(tagKey: TagKey<*>, entries: Collection<DynamicTagEntry>) {
		TagPostProcessorsImpl.register(tagKey, entries)
	}

	fun register(tagKey: TagKey<*>, configValue: Configurable<List<String>>) {
		TagPostProcessorsImpl.register(tagKey, configValue)
	}
}
