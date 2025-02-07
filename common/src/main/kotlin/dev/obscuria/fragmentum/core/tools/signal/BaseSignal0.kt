package dev.obscuria.fragmentum.core.tools.signal

import dev.obscuria.fragmentum.api.tools.signal.Signal0

class BaseSignal0 : BaseSignal<Signal0.Listener>(), Signal0
{
    override fun emit() = emit { it.consume() }
}