package dev.obscuria.fragmentum.fabric;

import dev.obscuria.fragmentum.api.v1.client.FragmentumClientEvents;
import dev.obscuria.fragmentum.core.CoreFragmentumClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public final class FabricFragmentumClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        CoreFragmentumClient.init();

        WorldRenderEvents.START.register(context -> FragmentumClientEvents.START_RENDER
                .broadcast(FragmentumClientEvents.WorldRender::invoke));
        WorldRenderEvents.END.register(context -> FragmentumClientEvents.END_RENDER
                .broadcast(FragmentumClientEvents.WorldRender::invoke));
        ClientTickEvents.START_WORLD_TICK.register(level -> FragmentumClientEvents.START_WORLD_TICK
                .broadcast(listener -> listener.invoke(level)));
        ClientTickEvents.END_WORLD_TICK.register(level -> FragmentumClientEvents.END_WORLD_TICK
                .broadcast(listener -> listener.invoke(level)));
        ClientTickEvents.START_CLIENT_TICK.register(minecraft -> FragmentumClientEvents.START_CLIENT_TICK
                .broadcast(listener -> listener.invoke(minecraft)));
        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> FragmentumClientEvents.END_CLIENT_TICK
                .broadcast(listener -> listener.invoke(minecraft)));
    }
}
