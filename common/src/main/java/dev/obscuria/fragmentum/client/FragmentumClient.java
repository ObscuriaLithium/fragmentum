package dev.obscuria.fragmentum.client;

import dev.obscuria.fragmentum.world.tooltip.GroupTooltip;

public interface FragmentumClient {

    static void init() {
        FragmentumClientRegistry.registerTooltipComponent(GroupTooltip.class, ClientGroupTooltip::create);
    }
}
