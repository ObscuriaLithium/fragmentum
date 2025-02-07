package dev.obscuria.fragmentum.api

import java.util.function.Supplier

interface ModBridge
{
    val id: String

    val displayName: String

    val isLoaded: Boolean

    infix fun <T> extract(supplier: Supplier<Supplier<T>>): T?

    infix fun invoke(supplier: Supplier<Runnable>): ModBridge

    infix fun fallback(runnable: Runnable): ModBridge

    companion object
    {
        fun create(id: String): ModBridge = Fragmentum.SERVICES.newModBridge(id, id)

        fun create(id: String, displayName: String): ModBridge = Fragmentum.SERVICES.newModBridge(id, displayName)
    }
}