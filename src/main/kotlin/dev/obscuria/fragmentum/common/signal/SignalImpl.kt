package dev.obscuria.fragmentum.common.signal

import dev.obscuria.fragmentum.Fragmentum
import dev.obscuria.fragmentum.api.common.singal.Signal
import dev.obscuria.fragmentum.api.common.singal.Signal0
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal abstract class SignalImpl<T> : Signal<T> {

	private val connections = mutableListOf<Connection<T>>()

	override fun connect(source: Any?, oneShot: Boolean, breaker: Signal0?, listener: T) {
		val connection = Connection(source ?: UNBOUND, oneShot, listener)
		connections.add(connection)
		if (breaker == null) return
		breaker.connect(true) { disconnect(connection) }
	}

	override fun disconnect(source: Any) {
		connections.removeIf {
			it.source === source
		}
	}

	protected fun emit(consumer: (T) -> Unit) {
		connections.removeIf {
			consumer.invoke(it.listener)
			it.oneShot
		}
	}

	private fun disconnect(connection: Connection<T>) {
		connections.remove(connection)
	}

	companion object {

		private val UNBOUND = Fragmentum.id("unbound")
	}

	private data class Connection<T>(
		val source: Any?,
		val oneShot: Boolean,
		val listener: T
	)
}
