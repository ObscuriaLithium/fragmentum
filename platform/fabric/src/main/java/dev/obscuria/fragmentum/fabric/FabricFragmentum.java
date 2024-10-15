package dev.obscuria.fragmentum.fabric;

import dev.obscuria.fragmentum.api.v1.server.ObscureServerEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public final class FabricFragmentum implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> ObscureServerEvents.SERVER_STARTING
                .broadcast(listener -> listener.invoke(server)));
        ServerLifecycleEvents.SERVER_STARTED.register(server -> ObscureServerEvents.SERVER_STARTED
                .broadcast(listener -> listener.invoke(server)));

        ServerLifecycleEvents.START_DATA_PACK_RELOAD.register((server, manager) -> ObscureServerEvents.START_DATA_PACK_RELOAD
                .broadcast(listener -> listener.invoke(server, manager)));
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, manager, success) -> ObscureServerEvents.END_DATA_PACK_RELOAD
                .broadcast(listener -> listener.invoke(server, manager, success)));
        ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register((player, joined) -> ObscureServerEvents.SYNC_DATA_PACK_CONTENTS
                .broadcast(listener -> listener.invoke(player, joined)));

        ServerLifecycleEvents.BEFORE_SAVE.register((server, flush, force) -> ObscureServerEvents.BEFORE_SAVE
                .broadcast(listener -> listener.invoke(server, flush, force)));
        ServerLifecycleEvents.AFTER_SAVE.register((server, flush, force) -> ObscureServerEvents.AFTER_SAVE
                .broadcast(listener -> listener.invoke(server, flush, force)));

        ServerLifecycleEvents.SERVER_STOPPING.register(server -> ObscureServerEvents.SERVER_STOPPING
                .broadcast(listener -> listener.invoke(server)));
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> ObscureServerEvents.SERVER_STOPPED
                .broadcast(listener -> listener.invoke(server)));
    }
}
