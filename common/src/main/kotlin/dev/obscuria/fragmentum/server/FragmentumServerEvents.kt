@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.server

import dev.obscuria.fragmentum.tools.event.Event
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.resources.CloseableResourceManager

object FragmentumServerEvents {

    val SERVER_STARTING: Event<Start> = Event()
    val SERVER_STARTED: Event<Start> = Event()

    val START_DATA_PACK_RELOAD: Event<StartDataReload> = Event()
    val END_DATA_PACK_RELOAD: Event<EndDataReload> = Event()
    val SYNC_DATA_PACK_CONTENTS: Event<SyncData> = Event()

    val BEFORE_SAVE: Event<Save> = Event()
    val AFTER_SAVE: Event<Save> = Event()

    val SERVER_STOPPING: Event<Stop> = Event()
    val SERVER_STOPPED: Event<Stop> = Event()

    fun interface Start {

        fun invoke(server: MinecraftServer)
    }

    fun interface StartDataReload {

        fun invoke(server: MinecraftServer, manager: CloseableResourceManager)
    }

    fun interface EndDataReload {

        fun invoke(server: MinecraftServer, manager: CloseableResourceManager, success: Boolean)
    }

    fun interface SyncData {

        fun invoke(player: ServerPlayer, joined: Boolean)
    }

    fun interface Save {

        fun invoke(server: MinecraftServer, flush: Boolean, force: Boolean)
    }

    fun interface Stop {

        fun invoke(server: MinecraftServer)
    }
}