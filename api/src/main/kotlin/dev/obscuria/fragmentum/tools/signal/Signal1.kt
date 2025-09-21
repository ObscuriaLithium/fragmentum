@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.tools.signal

class Signal1<P1> : Signal<Signal1.Listener<P1>>() {

    fun emit(p1: P1): Unit = error("API stub")

    fun interface Listener<P1> {

        fun consume(p1: P1)
    }
}