package dev.obscuria.fragmentum.core.tools.event

import dev.obscuria.fragmentum.api.tools.event.Event
import dev.obscuria.fragmentum.api.tools.event.EventToken
import java.util.*

@JvmRecord
data class BaseEventToken(val uuid: UUID, val event: Event<*>) : EventToken
{
    override fun unregister()
    {
        event.unregister(this)
    }
}
