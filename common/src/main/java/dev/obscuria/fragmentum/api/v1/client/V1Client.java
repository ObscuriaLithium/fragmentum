package dev.obscuria.fragmentum.api.v1.client;

import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jetbrains.annotations.ApiStatus;

import java.util.ServiceLoader;
import java.util.function.Function;

@ApiStatus.Internal
public interface V1Client
{
    V1Client INSTANCE = ServiceLoader.load(V1Client.class).findFirst().orElseThrow();

    IClientRegistrar registrar(String modId);

    <T extends TooltipComponent> void registerTooltipComponent(Class<T> type, Function<T, ClientTooltipComponent> factory);
}
