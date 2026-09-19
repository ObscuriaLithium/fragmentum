package dev.obscuria.fragmentum.common.tooltip

import dev.obscuria.fragmentum.api.common.color.rgbOf
import dev.obscuria.fragmentum.api.common.tooltip.ChromaPresets
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal object ChromaPresetsImpl {

	private val registry = mutableMapOf<String, ChromaPresets.Instance>()

	fun register(key: String, preset: ChromaPresets.Instance) {
		registry.putIfAbsent(key, preset)
	}

	fun get(key: String): ChromaPresets.Instance? {
		return registry[key]
	}

	init {
		register("gold", ChromaPresets.Instance(rgbOf("#e09704"), rgbOf("#ffc34a"), 4f))
	}
}
