@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.tools.signal

class Signal3<P1, P2, P3> : Signal<Signal3.Listener<P1, P2, P3>>() {

    fun emit(p1: P1, p2: P2, p3: P3): Unit = error("API stub")

    fun interface Listener<P1, P2, P3> {

        fun consume(p1: P1, p2: P2, p3: P3)
    }
}