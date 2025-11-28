package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.client.ClientRegistrar;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.function.Function;

public interface ClientService {

    ClientRegistrar registrar(String modId);

    <T extends TooltipComponent> void registerTooltipComponent(Class<T> type, Function<T, ClientTooltipComponent> factory);
}
