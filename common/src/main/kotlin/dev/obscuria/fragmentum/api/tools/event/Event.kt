package dev.obscuria.fragmentum.api.tools.event

import dev.obscuria.fragmentum.api.Fragmentum

interface Event<T>
{
    fun register(listener: T, priority: Int = 1000): EventToken

    infix fun unregister(token: EventToken)

    infix fun broadcast(handler: EventHandler<T>)

    companion object
    {
        fun <T> create(): Event<T> = Fragmentum.SERVICES.newEvent()
    }
}