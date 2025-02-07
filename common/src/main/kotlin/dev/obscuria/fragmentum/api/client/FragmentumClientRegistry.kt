package dev.obscuria.fragmentum.api.client

import dev.obscuria.fragmentum.api.Fragmentum
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent
import net.minecraft.world.inventory.tooltip.TooltipComponent
import java.util.function.Function

object FragmentumClientRegistry
{
    fun registrar(modId: String): ClientRegistrar =
        Fragmentum.SERVICES.client().registrar(modId)

    fun <T : TooltipComponent> registerTooltipComponent(
        type: Class<T>,
        factory: Function<T, ClientTooltipComponent>
    ) = Fragmentum.SERVICES.client().registerTooltipComponent(type, factory)
}