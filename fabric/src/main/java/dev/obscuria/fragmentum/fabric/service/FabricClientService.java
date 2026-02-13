package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.client.ClientRegistrar;
import dev.obscuria.fragmentum.client.TooltipComponentRegistry;
import dev.obscuria.fragmentum.fabric.registry.FabricClientRegistrar;
import dev.obscuria.fragmentum.service.ClientService;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.function.Function;

public final class FabricClientService implements ClientService {

    public static final FabricClientService INSTANCE = new FabricClientService();

    private FabricClientService() {}

    @Override
    public ClientRegistrar registrar(String modId) {
        return new FabricClientRegistrar(modId);
    }

    @Override
    public <T extends TooltipComponent> void registerTooltipComponent(Class<T> type, Function<T, ClientTooltipComponent> factory) {
        TooltipComponentRegistry.register(type, factory);
    }
}
