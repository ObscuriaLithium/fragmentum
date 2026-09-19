package dev.obscuria.fragmentum

import dev.obscuria.fragmentum.api.ModCompat
import org.jetbrains.annotations.ApiStatus
import java.util.function.Supplier

@ApiStatus.Internal
internal data class ModCompatImpl(
	override val modId: String,
	override val modName: String
) : ModCompat {

	override fun isLoaded(): Boolean {
		return Fragmentum.PLATFORM.isModLoaded(modId)
	}

	override fun runIfLoaded(runnable: Supplier<Runnable>) {
		if (!isLoaded()) return
		runnable.get().run()
	}

	override fun runIfMissing(runnable: Runnable) {
		if (isLoaded()) return
		runnable.run()
	}
}
