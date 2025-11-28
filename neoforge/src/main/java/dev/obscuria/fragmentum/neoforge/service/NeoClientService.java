package dev.obscuria.fragmentum.neoforge.service;

import dev.obscuria.fragmentum.client.ClientRegistrar;
import dev.obscuria.fragmentum.client.TooltipComponentRegistry;
import dev.obscuria.fragmentum.neoforge.registry.NeoClientRegistrar;
import dev.obscuria.fragmentum.service.ClientService;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.function.Function;

public final class NeoClientService implements ClientService {

    public static final NeoClientService INSTANCE = new NeoClientService();

    private NeoClientService() {}

    @Override
    public ClientRegistrar registrar(String modId) {
        return new NeoClientRegistrar(modId);
    }

    @Override
    public <T extends TooltipComponent> void registerTooltipComponent(Class<T> type, Function<T, ClientTooltipComponent> factory) {
        TooltipComponentRegistry.register(type, factory);
    }
}
