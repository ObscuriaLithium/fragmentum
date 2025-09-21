@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.tools.signal

class Signal0 : Signal<Signal0.Listener>() {

    fun emit(): Unit = error("API stub")

    fun interface Listener {

        fun consume()
    }
}