package dev.obscuria.fragmentum.fabric.service

import dev.obscuria.fragmentum.api.client.ClientRegistrar
import dev.obscuria.fragmentum.api.service.ClientService
import dev.obscuria.fragmentum.core.client.TooltipComponentRegistry
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent
import net.minecraft.world.inventory.tooltip.TooltipComponent
import java.util.function.Function

internal class SubFabricClient : ClientService
{
    override fun registrar(modId: String): ClientRegistrar = FabricClientRegistrar(modId)

    override fun <T : TooltipComponent> registerTooltipComponent(
        type: Class<T>,
        factory: Function<T, ClientTooltipComponent>
    )
    {
        TooltipComponentRegistry.register(type, factory)
    }

    companion object
    {
        val INSTANCE = SubFabricClient()
    }
}