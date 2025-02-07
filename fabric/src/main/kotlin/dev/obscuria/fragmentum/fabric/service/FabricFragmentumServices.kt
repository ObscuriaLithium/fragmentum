package dev.obscuria.fragmentum.fabric.service

import dev.obscuria.fragmentum.api.ModBridge
import dev.obscuria.fragmentum.api.registry.Registrar
import dev.obscuria.fragmentum.api.service.*
import dev.obscuria.fragmentum.api.tools.easing.CubicCurve
import dev.obscuria.fragmentum.api.tools.event.Event
import dev.obscuria.fragmentum.api.tools.signal.Signal0
import dev.obscuria.fragmentum.api.tools.signal.Signal1
import dev.obscuria.fragmentum.api.tools.signal.Signal2
import dev.obscuria.fragmentum.api.tools.signal.Signal3
import dev.obscuria.fragmentum.api.tools.text.TextWrapper
import dev.obscuria.fragmentum.core.client.BaseModBridge
import dev.obscuria.fragmentum.core.tools.event.BaseCubicCurve
import dev.obscuria.fragmentum.core.tools.event.BaseEvent
import dev.obscuria.fragmentum.core.tools.signal.BaseSignal0
import dev.obscuria.fragmentum.core.tools.signal.BaseSignal1
import dev.obscuria.fragmentum.core.tools.signal.BaseSignal2
import dev.obscuria.fragmentum.core.tools.signal.BaseSignal3
import dev.obscuria.fragmentum.core.tools.text.BaseTextWrapper

class FabricFragmentumServices : FragmentumServices
{
    override fun registrar(modId: String): Registrar = FabricRegistrar(modId)

    override fun factory(): FactoryService = SubFabricFactory.INSTANCE

    override fun network(): NetworkService = SubFabricNetwork.INSTANCE

    override fun server(): ServerService = SubFabricServer.INSTANCE

    override fun client(): ClientService = SubFabricClient.INSTANCE

    override fun <T> newEvent(): Event<T> = BaseEvent()

    override fun newSignal0(): Signal0 = BaseSignal0()

    override fun <P1> newSignal1(): Signal1<P1> = BaseSignal1()

    override fun <P1, P2> newSignal2(): Signal2<P1, P2> = BaseSignal2()

    override fun <P1, P2, P3> newSignal3(): Signal3<P1, P2, P3> = BaseSignal3()

    override fun newTextWrapper(text: String): TextWrapper = BaseTextWrapper(text)

    override fun newCubicCurse(resolution: Int): CubicCurve = BaseCubicCurve(resolution)

    override fun newModBridge(id: String, displayName: String): ModBridge = BaseModBridge(id, displayName)
}