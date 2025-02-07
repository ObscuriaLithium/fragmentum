package dev.obscuria.fragmentum.api.tools.signal

import dev.obscuria.fragmentum.api.Fragmentum

interface Signal<T>
{
    fun connect(listener: T, key: Any? = null, oneShot: Boolean = false, breaker: Signal0? = null)

    infix fun disconnect(key: Any)

    companion object
    {
        fun newSignal0(): Signal0 = Fragmentum.SERVICES.newSignal0()

        fun <P1> newSignal1(): Signal1<P1> = Fragmentum.SERVICES.newSignal1()

        fun <P1, P2> newSignal2(): Signal2<P1, P2> = Fragmentum.SERVICES.newSignal2()

        fun <P1, P2, P3> newSignal3(): Signal3<P1, P2, P3> = Fragmentum.SERVICES.newSignal3()
    }
}