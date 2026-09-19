package dev.obscuria.fragmentum.common.signal

import dev.obscuria.fragmentum.api.common.singal.Signal1
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class Signal1Impl<P1> :
	SignalImpl<Signal1.Listener<P1>>(),
	Signal1<P1> {

	override fun emit(p1: P1) {
		emit { it.consume(p1) }
	}
}
