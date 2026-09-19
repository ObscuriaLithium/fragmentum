package dev.obscuria.fragmentum.api.common.singal

import dev.obscuria.fragmentum.common.signal.Signal0Impl
import dev.obscuria.fragmentum.common.signal.Signal1Impl
import dev.obscuria.fragmentum.common.signal.Signal2Impl
import dev.obscuria.fragmentum.common.signal.Signal3Impl
import dev.obscuria.fragmentum.common.signal.Signal4Impl
import dev.obscuria.fragmentum.common.signal.Signal5Impl

interface Signal<T> {

	fun connect(listener: T) {
		connect(null, false, null, listener)
	}

	fun connect(oneShot: Boolean, listener: T) {
		connect(null, oneShot, null, listener)
	}

	fun connect(source: Any?, listener: T) {
		connect(source, false, null, listener)
	}

	fun connect(source: Any?, oneShot: Boolean, listener: T) {
		connect(source, oneShot, null, listener)
	}

	fun connect(source: Any?, oneShot: Boolean, breaker: Signal0?, listener: T)

	fun disconnect(source: Any)

	companion object {

		@JvmName("create0")
		fun create(): Signal0 {
			return Signal0Impl()
		}

		@JvmName("create1")
		fun <P1> create(): Signal1<P1> {
			return Signal1Impl()
		}

		@JvmName("create2")
		fun <P1, P2> create(): Signal2<P1, P2> {
			return Signal2Impl()
		}

		@JvmName("create3")
		fun <P1, P2, P3> create(): Signal3<P1, P2, P3> {
			return Signal3Impl()
		}

		@JvmName("create4")
		fun <P1, P2, P3, P4> create(): Signal4<P1, P2, P3, P4> {
			return Signal4Impl()
		}

		@JvmName("create5")
		fun <P1, P2, P3, P4, P5> create(): Signal5<P1, P2, P3, P4, P5> {
			return Signal5Impl()
		}
	}
}
