package dev.obscuria.fragmentum

import dev.obscuria.fragmentum.api.client.CompositeClientTooltipComponent
import dev.obscuria.fragmentum.api.client.FragmentumClientRegistry
import dev.obscuria.fragmentum.api.common.tooltip.CompositeTooltipComponent
import dev.obscuria.fragmentum.common.tooltip.CompositeTooltipComponentImpl
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal object FragmentumClient {

	fun onInitializeClient() {
		val registrar = FragmentumClientRegistry.registrar(Fragmentum.MOD_ID)
		registrar.registerTooltipComponent(CompositeTooltipComponentImpl::class.java, CompositeClientTooltipComponent::create)
		registrar.registerTooltipComponent(CompositeTooltipComponent::class.java, CompositeClientTooltipComponent::create)
	}
}
