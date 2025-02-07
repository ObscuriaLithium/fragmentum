package dev.obscuria.fragmentum.core.tools.signal

import com.google.common.collect.Lists
import dev.obscuria.fragmentum.api.tools.signal.Signal
import dev.obscuria.fragmentum.api.tools.signal.Signal0
import net.minecraft.resources.ResourceLocation
import java.util.function.Consumer

abstract class BaseSignal<T> : Signal<T>
{
    private val connections: MutableList<Connection<T>> = Lists.newArrayList()

    override fun connect(listener: T, key: Any?, oneShot: Boolean, breaker: Signal0?)
    {
        val connection = Connection(key ?: UNBOUND, listener, oneShot)
        connections.add(connection)
        breaker?.connect({ disconnect(connection) }, true)
    }

    override fun disconnect(key: Any)
    {
        connections.removeIf { connection: Connection<T> -> connection.key == key }
    }

    protected fun emit(consumer: Consumer<T>)
    {
        connections.removeIf { connection: Connection<T> ->
            consumer.accept(connection.listener)
            connection.oneShot
        }
    }

    private fun disconnect(connection: Connection<T>)
    {
        connections.remove(connection)
    }

    @JvmRecord
    protected data class Connection<T>(val key: Any, val listener: T, val oneShot: Boolean)

    companion object
    {
        private val UNBOUND = ResourceLocation("unbound")
    }
}