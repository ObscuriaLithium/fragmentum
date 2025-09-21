@file:Suppress("unused", "RedundantNullableReturnType", "MemberVisibilityCanBePrivate")

package dev.obscuria.fragmentum.tools.signal

import net.minecraft.resources.ResourceLocation

abstract class Signal<T> {

    protected val connections: MutableList<Connection<T>> = mutableListOf()

    fun connect(listener: T) {
        connect(null, false, null, listener)
    }

    fun connect(key: Any?, listener: T) {
        connect(key, false, null, listener)
    }

    fun connect(oneShot: Boolean, listener: T) {
        connect(null, oneShot, null, listener)
    }

    fun connect(key: Any?, oneShot: Boolean, listener: T) {
        connect(key, oneShot, null, listener)
    }

    fun connect(key: Any?, oneShot: Boolean, breaker: Signal0?, listener: T) {
        val connection = Connection(key ?: UNBOUND, listener, oneShot)
        connections.add(connection)
        breaker?.connect(true) { disconnect(connection) }
    }

    infix fun disconnect(key: Any) {
        connections.removeIf { connection: Connection<T> -> connection.key == key }
    }

    protected inline fun emit(crossinline consumer: (T) -> Unit) {
        connections.removeIf {
            consumer(it.listener)
            it.oneShot
        }
    }

    private fun disconnect(connection: Connection<T>) {
        connections.remove(connection)
    }

    @JvmRecord
    protected data class Connection<T>(val key: Any, val listener: T, val oneShot: Boolean)

    companion object {

        private val UNBOUND = ResourceLocation("unbound")
    }
}