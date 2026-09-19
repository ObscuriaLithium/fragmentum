package dev.obscuria.fragmentum.common.signal

import dev.obscuria.fragmentum.api.common.singal.Signal4
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class Signal4Impl<P1, P2, P3, P4> :
	SignalImpl<Signal4.Listener<P1, P2, P3, P4>>(),
	Signal4<P1, P2, P3, P4> {

	override fun emit(p1: P1, p2: P2, p3: P3, p4: P4) {
		emit { it.consume(p1, p2, p3, p4) }
	}
}
