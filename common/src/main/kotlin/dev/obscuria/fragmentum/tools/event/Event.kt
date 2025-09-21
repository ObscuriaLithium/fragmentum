package dev.obscuria.fragmentum.tools.event

import java.util.*

class Event<T> {

    private val registrations: MutableList<Registration<T>> = mutableListOf()
    private var dirty = false

    infix fun register(listener: T): EventToken {
        return register(1000, listener)
    }

    fun register(priority: Int, listener: T): EventToken {
        val token = Token(UUID.randomUUID(), this)
        registrations.add(Registration(token, priority, listener))
        this.dirty = true
        return token
    }

    infix fun unregister(token: EventToken) {
        registrations.removeIf { it.token == token }
    }

    infix fun broadcast(handler: EventHandler<T>) {
        this.sortIfDirty()
        registrations.forEach { handler.handle(it.listener) }
    }

    private fun sortIfDirty() {
        if (!dirty) return
        registrations.sortWith(Registration<T>::compareTo)
        this.dirty = false
    }

    private class Registration<T>(
        val token: EventToken,
        val priority: Int,
        val listener: T
    ) : Comparable<Registration<T>> {

        override fun compareTo(other: Registration<T>): Int {
            return this.priority.compareTo(other.priority)
        }
    }

    data class Token(val uuid: UUID, val event: Event<*>) : EventToken
    {
        override fun unregister()
        {
            event.unregister(this)
        }
    }
}