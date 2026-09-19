package dev.obscuria.fragmentum.common.signal

import dev.obscuria.fragmentum.api.common.singal.Signal3
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class Signal3Impl<P1, P2, P3> :
	SignalImpl<Signal3.Listener<P1, P2, P3>>(),
	Signal3<P1, P2, P3> {

	override fun emit(p1: P1, p2: P2, p3: P3) {
		emit { it.consume(p1, p2, p3) }
	}
}
