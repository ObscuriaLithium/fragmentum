@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.tools.signal

class Signal4<P1, P2, P3, P4> : Signal<Signal4.Listener<P1, P2, P3, P4>>() {

    fun emit(p1: P1, p2: P2, p3: P3, p4: P4): Unit = error("API stub")

    fun interface Listener<P1, P2, P3, P4> {

        fun consume(p1: P1, p2: P2, p3: P3, p4: P4)
    }
}