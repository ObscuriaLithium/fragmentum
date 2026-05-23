package dev.obscuria.fragmentum.fabric.client;

import dev.obscuria.fragmentum.client.FragmentumClient;
import net.fabricmc.api.ClientModInitializer;

public final class FabricFragmentumClient implements ClientModInitializer {

    @Override public void onInitializeClient() {
        FragmentumClient.init();
    }
}
