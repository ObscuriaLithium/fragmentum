package dev.obscuria.fragmentum

import java.nio.file.Path

interface Platform {

	fun isModLoaded(modId: String): Boolean

	fun isDevelopmentEnvironment(): Boolean

	fun isClient(): Boolean

	fun isDedicatedServer(): Boolean

	fun configDir(): Path

	fun loader(): ModLoader

	enum class ModLoader {
		FABRIC,
		NEOFORGE,
		FORGE,
		QUILT
	}
}
