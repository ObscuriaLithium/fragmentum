package dev.obscuria.fragmentum

import dev.obscuria.fragmentum.client.FragmentumClientRegistry
import dev.obscuria.fragmentum.client.AllOfClientTooltipComponent
import dev.obscuria.fragmentum.world.AllOfTooltipComponent

object FragmentumClient {

    fun init() {

        FragmentumClientRegistry.registerTooltipComponent(AllOfTooltipComponent::class.java) {
            AllOfClientTooltipComponent(it)
        }
    }
}