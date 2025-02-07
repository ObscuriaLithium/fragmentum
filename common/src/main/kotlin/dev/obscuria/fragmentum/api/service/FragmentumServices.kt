package dev.obscuria.fragmentum.api.service

import dev.obscuria.fragmentum.api.ModBridge
import dev.obscuria.fragmentum.api.registry.Registrar
import dev.obscuria.fragmentum.api.tools.easing.CubicCurve
import dev.obscuria.fragmentum.api.tools.event.Event
import dev.obscuria.fragmentum.api.tools.signal.Signal0
import dev.obscuria.fragmentum.api.tools.signal.Signal1
import dev.obscuria.fragmentum.api.tools.signal.Signal2
import dev.obscuria.fragmentum.api.tools.signal.Signal3
import dev.obscuria.fragmentum.api.tools.text.TextWrapper

interface FragmentumServices
{
    fun registrar(modId: String): Registrar

    fun factory(): FactoryService

    fun network(): NetworkService

    fun server(): ServerService

    fun client(): ClientService

    fun <T> newEvent(): Event<T>

    fun newSignal0(): Signal0

    fun <P1> newSignal1(): Signal1<P1>

    fun <P1, P2> newSignal2(): Signal2<P1, P2>

    fun <P1, P2, P3> newSignal3(): Signal3<P1, P2, P3>

    fun newTextWrapper(text: String): TextWrapper

    fun newCubicCurse(resolution: Int): CubicCurve

    fun newModBridge(id: String, displayName: String): ModBridge
}