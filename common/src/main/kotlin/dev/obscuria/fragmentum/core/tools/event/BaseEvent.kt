package dev.obscuria.fragmentum.core.tools.event

import com.google.common.collect.Lists
import dev.obscuria.fragmentum.api.tools.event.Event
import dev.obscuria.fragmentum.api.tools.event.EventHandler
import dev.obscuria.fragmentum.api.tools.event.EventToken
import java.util.*

class BaseEvent<T> : Event<T>
{
    private val registrations: MutableList<Registration<T>> = Lists.newArrayList()
    private var dirty = false

    override fun register(listener: T, priority: Int): EventToken
    {
        val token = BaseEventToken(UUID.randomUUID(), this)
        registrations.add(Registration(token, priority, listener))
        this.dirty = true
        return token
    }

    override fun unregister(token: EventToken)
    {
        registrations.removeIf { it.token == token }
    }

    override fun broadcast(handler: EventHandler<T>)
    {
        this.sortIfDirty()
        registrations.stream()
            .map { it.listener }
            .forEach(handler::handle)
    }

    private fun sortIfDirty()
    {
        if (!dirty) return
        registrations.sortWith(Registration<T>::compareTo)
        this.dirty = false
    }

    private class Registration<T>(
        val token: EventToken,
        val priority: Int,
        val listener: T
    ) : Comparable<Registration<T>>
    {
        override fun compareTo(other: Registration<T>): Int
        {
            return this.priority.compareTo(other.priority)
        }
    }
}