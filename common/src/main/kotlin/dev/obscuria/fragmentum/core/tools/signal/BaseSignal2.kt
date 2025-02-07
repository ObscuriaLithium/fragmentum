package dev.obscuria.fragmentum.core.tools.signal

import dev.obscuria.fragmentum.api.tools.signal.Signal2

class BaseSignal2<P1, P2> : BaseSignal<Signal2.Listener<P1, P2>>(), Signal2<P1, P2>
{
    override fun emit(p1: P1, p2: P2) = emit { it.consume(p1, p2) }
}