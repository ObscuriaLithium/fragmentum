package dev.obscuria.fragmentum.api

import dev.obscuria.fragmentum.ModCompatImpl
import java.util.function.Supplier

interface ModCompat {

	val modId: String

	val modName: String

	fun isLoaded(): Boolean

	fun runIfLoaded(runnable: Supplier<Runnable>)

	fun runIfMissing(runnable: Runnable)

	companion object {

		fun create(modId: String): ModCompat {
			return create(modId, modId)
		}

		fun create(modId: String, modName: String): ModCompat {
			return ModCompatImpl(modId, modName)
		}
	}
}
