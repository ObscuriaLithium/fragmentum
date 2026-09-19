package dev.obscuria.fragmentum.common.signal

import dev.obscuria.fragmentum.api.common.singal.Signal5
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class Signal5Impl<P1, P2, P3, P4, P5> :
	SignalImpl<Signal5.Listener<P1, P2, P3, P4, P5>>(),
	Signal5<P1, P2, P3, P4, P5> {

	override fun emit(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) {
		emit { it.consume(p1, p2, p3, p4, p5) }
	}
}
