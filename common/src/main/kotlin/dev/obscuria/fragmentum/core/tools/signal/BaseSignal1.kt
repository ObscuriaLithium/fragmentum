package dev.obscuria.fragmentum.core.tools.signal

import dev.obscuria.fragmentum.api.tools.signal.Signal1

class BaseSignal1<P1> : BaseSignal<Signal1.Listener<P1>>(), Signal1<P1>
{
    override fun emit(p1: P1) = emit { it.consume(p1) }
}