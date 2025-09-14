package dev.obscuria.fragmentum.core.client

import dev.obscuria.fragmentum.api.Fragmentum
import dev.obscuria.fragmentum.api.ModCompat

data class BaseModCompat(
    override val id: String,
    override val displayName: String
) : ModCompat {

    override val isLoaded: Boolean
        get() = Fragmentum.PLATFORM.isModLoaded(id)

    override fun <T> getIfLoaded(supplier: () -> () -> T): T? {
        return if (isLoaded) return supplier()() else null
    }

    override fun runIfLoaded(runnable: () -> () -> Any) {
        if (!isLoaded) return
        runnable()()
    }

    override fun runIfMissing(runnable: () -> Any) {
        if (isLoaded) return
        runnable()
    }
}