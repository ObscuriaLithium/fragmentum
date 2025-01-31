package dev.obscuria.fragmentum.forge.service;

import dev.obscuria.fragmentum.api.v1.client.IClientRegistrar;
import dev.obscuria.fragmentum.api.v1.client.V1Client;
import dev.obscuria.fragmentum.core.v1.client.TooltipComponentFactories;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Function;

@ApiStatus.Internal
public final class ForgeV1Client implements V1Client
{
    @Override
    public IClientRegistrar registrar(String modId)
    {
        return new ForgeClientRegistrar(modId);
    }

    @Override
    public <T extends TooltipComponent> void registerTooltipComponent(Class<T> type, Function<T, ClientTooltipComponent> factory)
    {
        TooltipComponentFactories.register(type, factory);
    }
}
