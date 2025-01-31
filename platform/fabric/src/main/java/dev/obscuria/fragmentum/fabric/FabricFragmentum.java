package dev.obscuria.fragmentum.fabric;

import dev.obscuria.fragmentum.api.v1.server.FragmentumServerEvents;
import dev.obscuria.fragmentum.core.CoreFragmentum;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class FabricFragmentum implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        CoreFragmentum.init();

        ServerLifecycleEvents.SERVER_STARTING.register(server -> FragmentumServerEvents.SERVER_STARTING
                .broadcast(listener -> listener.invoke(server)));
        ServerLifecycleEvents.SERVER_STARTED.register(server -> FragmentumServerEvents.SERVER_STARTED
                .broadcast(listener -> listener.invoke(server)));
        ServerLifecycleEvents.START_DATA_PACK_RELOAD.register((server, manager) -> FragmentumServerEvents.START_DATA_PACK_RELOAD
                .broadcast(listener -> listener.invoke(server, manager)));
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, manager, success) -> FragmentumServerEvents.END_DATA_PACK_RELOAD
                .broadcast(listener -> listener.invoke(server, manager, success)));
        ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register((player, joined) -> FragmentumServerEvents.SYNC_DATA_PACK_CONTENTS
                .broadcast(listener -> listener.invoke(player, joined)));
        ServerLifecycleEvents.BEFORE_SAVE.register((server, flush, force) -> FragmentumServerEvents.BEFORE_SAVE
                .broadcast(listener -> listener.invoke(server, flush, force)));
        ServerLifecycleEvents.AFTER_SAVE.register((server, flush, force) -> FragmentumServerEvents.AFTER_SAVE
                .broadcast(listener -> listener.invoke(server, flush, force)));
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> FragmentumServerEvents.SERVER_STOPPING
                .broadcast(listener -> listener.invoke(server)));
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> FragmentumServerEvents.SERVER_STOPPED
                .broadcast(listener -> listener.invoke(server)));
    }
}
