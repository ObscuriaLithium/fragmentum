package dev.obscuria.fragmentum.core

import dev.obscuria.fragmentum.api.client.FragmentumClientRegistry
import dev.obscuria.fragmentum.core.client.AllOfClientTooltipComponent
import dev.obscuria.fragmentum.core.world.AllOfTooltipComponent
import org.jetbrains.annotations.ApiStatus.Internal

@Internal
object CoreFragmentumClient {

    fun init() {
        FragmentumClientRegistry.registerTooltipComponent(AllOfTooltipComponent::class.java) {
            AllOfClientTooltipComponent(it)
        }
    }
}