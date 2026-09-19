package dev.obscuria.fragmentum.api.common.tooltip

import dev.obscuria.fragmentum.api.common.color.RGB
import dev.obscuria.fragmentum.common.tooltip.ChromaPresetsImpl

object ChromaPresets {

	fun register(key: String, preset: Instance) {
		ChromaPresetsImpl.register(key, preset)
	}

	fun get(key: String): Instance? {
		return ChromaPresetsImpl.get(key)
	}

	data class Instance(
		val first: RGB,
		val second: RGB,
		val speed: Float
	)
}
