package dev.obscuria.fragmentum.tools.signal

class Signal0 : Signal<Signal0.Listener>() {

    fun emit() = emit { it.consume() }

    fun interface Listener {

        fun consume()
    }
}