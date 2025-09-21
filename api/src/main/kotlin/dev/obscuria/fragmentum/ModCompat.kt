@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType")

package dev.obscuria.fragmentum

class ModCompat {

    constructor(modId: String)

    constructor(modId: String, displayName: String)

    val modId: String get() = error("API stub")

    val displayName: String get() = error("API stub")

    val isLoaded: Boolean get() = error("API stub")

    inline fun <T> getIfLoaded(supplier: () -> () -> T): T? = error("API stub")

    inline fun runIfLoaded(runnable: () -> () -> Any): Unit = error("API stub")

    inline fun runIfMissing(runnable: () -> Any): Unit = error("API stub")
}