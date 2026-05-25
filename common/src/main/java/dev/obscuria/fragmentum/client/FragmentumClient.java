package dev.obscuria.fragmentum.client;

import dev.obscuria.fragmentum.v2.api.client.ClientTooltipStack;
import dev.obscuria.fragmentum.v2.api.client.TooltipComponentRegistry;
import dev.obscuria.fragmentum.v2.api.common.TooltipStack;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.ApiStatus;

@UtilityClass
@ApiStatus.Internal
public final class FragmentumClient {

    public static void init() {
        TooltipComponentRegistry.register(TooltipStack.class, ClientTooltipStack::create);
    }
}
