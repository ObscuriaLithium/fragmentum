package dev.obscuria.fragmentum.api.tools.signal

interface Signal0 : Signal<Signal0.Listener> {

    fun emit()

    fun interface Listener {

        fun consume()
    }
}