package dev.obscuria.fragmentum.forge.service;

import dev.obscuria.fragmentum.client.ClientRegistrar;
import dev.obscuria.fragmentum.client.TooltipComponentRegistry;
import dev.obscuria.fragmentum.forge.registry.ForgeClientRegistrar;
import dev.obscuria.fragmentum.service.ClientService;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.function.Function;

public final class ForgeClientService implements ClientService
{
    public static final ForgeClientService INSTANCE = new ForgeClientService();

    private ForgeClientService() {}

    @Override
    public ClientRegistrar registrar(String modId)
    {
        return new ForgeClientRegistrar(modId);
    }

    @Override
    public <T extends TooltipComponent> void registerTooltipComponent(Class<T> type, Function<T, ClientTooltipComponent> factory)
    {
        TooltipComponentRegistry.register(type, factory);
    }
}
