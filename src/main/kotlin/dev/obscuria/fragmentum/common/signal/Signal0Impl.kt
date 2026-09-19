package dev.obscuria.fragmentum.common.signal

import dev.obscuria.fragmentum.api.common.singal.Signal0
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class Signal0Impl : SignalImpl<Signal0.Listener>(), Signal0 {

	override fun emit() {
		emit(Signal0.Listener::consume)
	}
}
