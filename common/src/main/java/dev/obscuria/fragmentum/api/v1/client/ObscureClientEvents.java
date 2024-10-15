package dev.obscuria.fragmentum.api.v1.client;

import dev.obscuria.fragmentum.api.v1.common.event.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;

public interface ObscureClientEvents
{
    Event<WorldRender> START_RENDER = Event.create();
    Event<WorldRender> END_RENDER = Event.create();

    Event<WorldTick> START_WORLD_TICK = Event.create();
    Event<WorldTick> END_WORLD_TICK = Event.create();

    Event<ClientTick> START_CLIENT_TICK = Event.create();
    Event<ClientTick> END_CLIENT_TICK = Event.create();

    @FunctionalInterface
    interface WorldRender
    {
        void invoke();
    }

    @FunctionalInterface
    interface WorldTick
    {
        void invoke(ClientLevel level);
    }

    @FunctionalInterface
    interface ClientTick
    {
        void invoke(Minecraft minecraft);
    }
}
