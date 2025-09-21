package dev.obscuria.fragmentum.service

import dev.obscuria.fragmentum.client.ClientRegistrar
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent
import net.minecraft.world.inventory.tooltip.TooltipComponent
import java.util.function.Function

interface ClientService {

    fun registrar(modId: String): ClientRegistrar

    fun <T : TooltipComponent> registerTooltipComponent(type: Class<T>, factory: Function<T, ClientTooltipComponent>)
}