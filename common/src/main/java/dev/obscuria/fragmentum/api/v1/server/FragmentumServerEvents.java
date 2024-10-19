package dev.obscuria.fragmentum.api.v1.server;

import dev.obscuria.fragmentum.api.v1.common.event.Event;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.CloseableResourceManager;

public interface FragmentumServerEvents
{
    Event<Start> SERVER_STARTING = Event.create();
    Event<Start> SERVER_STARTED = Event.create();

    Event<StartDataReload> START_DATA_PACK_RELOAD = Event.create();
    Event<EndDataReload> END_DATA_PACK_RELOAD = Event.create();
    Event<SyncData> SYNC_DATA_PACK_CONTENTS = Event.create();

    Event<Save> BEFORE_SAVE = Event.create();
    Event<Save> AFTER_SAVE = Event.create();

    Event<Stop> SERVER_STOPPING = Event.create();
    Event<Stop> SERVER_STOPPED = Event.create();

    @FunctionalInterface
    interface Start
    {
        void invoke(MinecraftServer server);
    }

    @FunctionalInterface
    interface StartDataReload
    {
        void invoke(MinecraftServer server, CloseableResourceManager manager);
    }

    @FunctionalInterface
    interface EndDataReload
    {
        void invoke(MinecraftServer server, CloseableResourceManager manager, boolean success);
    }

    @FunctionalInterface
    interface SyncData
    {
        void invoke(ServerPlayer player, boolean joined);
    }

    @FunctionalInterface
    interface Save
    {
        void invoke(MinecraftServer server, boolean flush, boolean force);
    }

    @FunctionalInterface
    interface Stop
    {
        void invoke(MinecraftServer server);
    }
}
