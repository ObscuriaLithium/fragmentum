package dev.obscuria.fragmentum.core.tools.signal

import dev.obscuria.fragmentum.api.tools.signal.Signal3

class BaseSignal3<P1, P2, P3> : BaseSignal<Signal3.Listener<P1, P2, P3>>(), Signal3<P1, P2, P3>
{
    override fun emit(p1: P1, p2: P2, p3: P3) = emit { it.consume(p1, p2, p3) }
}