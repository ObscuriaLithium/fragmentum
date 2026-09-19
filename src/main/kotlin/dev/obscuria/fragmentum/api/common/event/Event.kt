package dev.obscuria.fragmentum.api.common.event

import dev.obscuria.fragmentum.common.EventImpl

interface Event<T> {

	fun register(listener: T): EventToken {
		return register(EventPriority.DEFAULT, listener)
	}

	fun register(priority: EventPriority, listener: T): EventToken {
		return register(priority.value, listener)
	}

	fun register(priority: Int, listener: T): EventToken

	fun unregister(token: EventToken)

	fun broadcast(handler: EventHandler<T>)

	companion object {

		fun <T> create(): Event<T> {
			return EventImpl()
		}
	}
}
