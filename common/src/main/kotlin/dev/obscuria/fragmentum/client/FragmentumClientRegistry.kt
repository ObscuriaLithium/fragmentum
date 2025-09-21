package dev.obscuria.fragmentum.client

import dev.obscuria.fragmentum.Fragmentum
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent
import net.minecraft.world.inventory.tooltip.TooltipComponent
import java.util.function.Function

object FragmentumClientRegistry {

    fun registrar(modId: String): ClientRegistrar {
        return Fragmentum.SERVICES.client().registrar(modId)
    }

    fun <T : TooltipComponent> registerTooltipComponent(
        type: Class<T>,
        factory: Function<T, ClientTooltipComponent>
    ) {
        Fragmentum.SERVICES.client().registerTooltipComponent(type, factory)
    }
}