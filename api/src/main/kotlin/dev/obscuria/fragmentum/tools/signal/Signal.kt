@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.tools.signal

abstract class Signal<T> {

    fun connect(listener: T): Unit = error("API stub")

    fun connect(key: Any?, listener: T): Unit = error("API stub")

    fun connect(oneShot: Boolean, listener: T): Unit = error("API stub")

    fun connect(key: Any?, oneShot: Boolean, listener: T): Unit = error("API stub")

    fun connect(listener: T, key: Any? = null, oneShot: Boolean = false, breaker: Signal0? = null): Unit = error("API stub")

    infix fun disconnect(key: Any): Unit = error("API stub")
}