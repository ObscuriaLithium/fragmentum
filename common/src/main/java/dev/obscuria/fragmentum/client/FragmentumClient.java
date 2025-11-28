package dev.obscuria.fragmentum.client;

import dev.obscuria.fragmentum.content.world.tooltip.GroupTooltip;

public interface FragmentumClient {

    static void init() {
        FragmentumClientRegistry.registerTooltipComponent(GroupTooltip.class, ClientGroupTooltip::create);
    }
}
