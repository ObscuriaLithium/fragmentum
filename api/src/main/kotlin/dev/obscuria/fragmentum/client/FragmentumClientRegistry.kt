@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.client

import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent
import net.minecraft.world.inventory.tooltip.TooltipComponent
import java.util.function.Function

object FragmentumClientRegistry {

    fun registrar(modId: String): ClientRegistrar = error("API stub")

    fun <T : TooltipComponent> registerTooltipComponent(
        type: Class<T>,
        factory: Function<T, ClientTooltipComponent>
    ): Unit = error("API stub")
}