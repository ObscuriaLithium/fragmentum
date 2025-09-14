package dev.obscuria.fragmentum.api

interface ModCompat {

    val id: String

    val displayName: String

    val isLoaded: Boolean

    infix fun <T> getIfLoaded(supplier: () -> () -> T): T?

    infix fun runIfLoaded(runnable: () -> () -> Any)

    infix fun runIfMissing(runnable: () -> Any)

    companion object {

        fun create(id: String): ModCompat = Fragmentum.SERVICES.newModBridge(id, id)

        fun create(id: String, displayName: String): ModCompat = Fragmentum.SERVICES.newModBridge(id, displayName)
    }
}