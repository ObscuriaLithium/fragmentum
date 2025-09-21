package dev.obscuria.fragmentum.tools.signal

class Signal2<P1, P2> : Signal<Signal2.Listener<P1, P2>>() {

    fun emit(p1: P1, p2: P2) = emit { it.consume(p1, p2) }

    fun interface Listener<P1, P2> {

        fun consume(p1: P1, p2: P2)
    }
}