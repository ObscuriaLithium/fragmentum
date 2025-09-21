@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum

class ModCompat(
    val modId: String,
    val displayName: String
) {

    val isLoaded: Boolean get() = Fragmentum.PLATFORM.isModLoaded(modId)

    constructor(modId: String) : this(modId, modId)

    inline fun <T> getIfLoaded(supplier: () -> () -> T): T? {
        if (isLoaded) return supplier()()
        return null
    }

    inline fun runIfLoaded(runnable: () -> () -> Any) {
        if (!isLoaded) return
        runnable()()
    }

    inline fun runIfMissing(runnable: () -> Any) {
        if (isLoaded) return
        runnable()
    }
}