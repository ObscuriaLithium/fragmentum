@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType", "MemberVisibilityCanBePrivate")

package dev.obscuria.fragmentum.tools.event

class Event<T> {

    infix fun register(listener: T): EventToken = register(1000, listener)

    fun register(priority: Int, listener: T): EventToken = error("API stub")

    infix fun unregister(token: EventToken): Unit = error("API stub")

    infix fun broadcast(handler: EventHandler<T>): Unit = error("API stub")
}