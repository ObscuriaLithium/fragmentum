package dev.obscuria.fragmentum.fabric.service

import dev.obscuria.fragmentum.client.ClientRegistrar
import dev.obscuria.fragmentum.service.ClientService
import dev.obscuria.fragmentum.client.TooltipComponentRegistry
import dev.obscuria.fragmentum.fabric.impl.FabricClientRegistrar
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent
import net.minecraft.world.inventory.tooltip.TooltipComponent
import java.util.function.Function

internal object FabricClientService : ClientService {

    override fun registrar(modId: String): ClientRegistrar {
        return FabricClientRegistrar(modId)
    }

    override fun <T : TooltipComponent> registerTooltipComponent(
        type: Class<T>,
        factory: Function<T, ClientTooltipComponent>
    ) {
        TooltipComponentRegistry.register(type, factory)
    }
}