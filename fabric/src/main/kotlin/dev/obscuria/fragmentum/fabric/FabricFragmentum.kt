package dev.obscuria.fragmentum.fabric

import dev.obscuria.fragmentum.core.CoreFragmentum
import dev.obscuria.fragmentum.core.CoreFragmentumClient
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.*
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents.END
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents.START
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.*
import dev.obscuria.fragmentum.api.client.FragmentumClientEvents as Client
import dev.obscuria.fragmentum.api.server.FragmentumServerEvents as Server

fun commonInit() {

    CoreFragmentum.init()

    SERVER_STARTING.register { s -> Server.SERVER_STARTING broadcast { it.invoke(s) } }
    SERVER_STARTED.register { s -> Server.SERVER_STARTED broadcast { it.invoke(s) } }
    START_DATA_PACK_RELOAD.register { s, m -> Server.START_DATA_PACK_RELOAD broadcast { it.invoke(s, m) } }
    END_DATA_PACK_RELOAD.register { s, m, b -> Server.END_DATA_PACK_RELOAD broadcast { it.invoke(s, m, b) } }
    SYNC_DATA_PACK_CONTENTS.register { s, b -> Server.SYNC_DATA_PACK_CONTENTS broadcast { it.invoke(s, b) } }
    SERVER_STOPPING.register { s -> Server.SERVER_STOPPING broadcast { it.invoke(s) } }
    SERVER_STOPPED.register { s -> Server.SERVER_STOPPED broadcast { it.invoke(s) } }
}

fun clientInit() {

    CoreFragmentumClient.init()

    START.register { Client.START_RENDER.broadcast { it.invoke() } }
    END.register { Client.END_RENDER.broadcast { it.invoke() } }
    START_WORLD_TICK.register { l -> Client.START_WORLD_TICK.broadcast { it.invoke(l) } }
    END_WORLD_TICK.register { l -> Client.END_WORLD_TICK.broadcast { it.invoke(l) } }
    START_CLIENT_TICK.register { c -> Client.START_CLIENT_TICK.broadcast { it.invoke(c) } }
    END_CLIENT_TICK.register { c -> Client.END_CLIENT_TICK.broadcast { it.invoke(c) } }
}