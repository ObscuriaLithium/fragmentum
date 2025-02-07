package dev.obscuria.fragmentum.core.client

import dev.obscuria.fragmentum.api.Fragmentum
import dev.obscuria.fragmentum.api.ModBridge
import java.util.function.Supplier

@JvmRecord
data class BaseModBridge(override val id: String, override val displayName: String) : ModBridge
{
    override val isLoaded: Boolean
        get() = Fragmentum.PLATFORM.isModLoaded(id)

    override fun <T> extract(supplier: Supplier<Supplier<T>>): T?
    {
        return if (isLoaded) return supplier.get().get() else null
    }

    override fun invoke(supplier: Supplier<Runnable>): ModBridge
    {
        if (!isLoaded) return this
        supplier.get().run()
        return this
    }

    override fun fallback(runnable: Runnable): ModBridge
    {
        if (isLoaded) return this
        runnable.run()
        return this
    }
}