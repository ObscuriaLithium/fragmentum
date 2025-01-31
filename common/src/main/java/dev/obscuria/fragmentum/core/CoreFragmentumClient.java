package dev.obscuria.fragmentum.core;

import dev.obscuria.fragmentum.api.v1.client.FragmentumClientRegistry;
import dev.obscuria.fragmentum.core.v1.client.AllOfClientTooltipComponent;
import dev.obscuria.fragmentum.core.v1.common.AllOfTooltipComponent;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class CoreFragmentumClient
{
    public static void init()
    {
        FragmentumClientRegistry.registerTooltipComponent(AllOfTooltipComponent.class, AllOfClientTooltipComponent::new);
    }
}
