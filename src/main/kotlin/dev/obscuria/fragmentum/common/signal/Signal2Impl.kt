package dev.obscuria.fragmentum.common.signal

import dev.obscuria.fragmentum.api.common.singal.Signal2
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class Signal2Impl<P1, P2> :
	SignalImpl<Signal2.Listener<P1, P2>>(),
	Signal2<P1, P2> {

	override fun emit(p1: P1, p2: P2) {
		emit { it.consume(p1, p2) }
	}
}
