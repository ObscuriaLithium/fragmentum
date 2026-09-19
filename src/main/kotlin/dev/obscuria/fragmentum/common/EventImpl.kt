package dev.obscuria.fragmentum.common

import dev.obscuria.fragmentum.api.common.event.Event
import dev.obscuria.fragmentum.api.common.event.EventHandler
import dev.obscuria.fragmentum.api.common.event.EventToken
import org.jetbrains.annotations.ApiStatus
import java.util.*

@ApiStatus.Internal
internal class EventImpl<T> : Event<T> {

	private val registrations = mutableListOf<Registration<T>>()
	private var dirty = false

	override fun register(priority: Int, listener: T): EventToken {
		val token = Token(UUID.randomUUID(), this)
		registrations.add(Registration(token, priority, listener))
		dirty = true
		return token
	}

	override fun unregister(token: EventToken) {
		registrations.removeIf {
			it.token === token
		}
	}

	override fun broadcast(handler: EventHandler<T>) {
		sortIfDirty()
		registrations.toList().forEach {
			handler.handle(it.listener)
		}
	}

	private fun sortIfDirty() {
		if (!dirty) return
		registrations.sort()
		dirty = false
	}

	private class Registration<T>(
		val token: EventToken,
		val priority: Int,
		val listener: T
	) : Comparable<Registration<T>> {

		override fun compareTo(other: Registration<T>): Int {
			return priority.compareTo(other.priority)
		}
	}

	private data class Token(
		val uuid: UUID,
		val event: EventImpl<*>
	) : EventToken {

		override fun unregister() {
			event.unregister(this)
		}
	}
}
