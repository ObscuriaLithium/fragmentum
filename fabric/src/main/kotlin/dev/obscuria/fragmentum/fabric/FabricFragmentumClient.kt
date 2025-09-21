package dev.obscuria.fragmentum.fabric

import dev.obscuria.fragmentum.FragmentumClient
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.*
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents.END
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents.START
import dev.obscuria.fragmentum.client.FragmentumClientEvents as Client

fun clientInit() {

    FragmentumClient.init()

    START.register { Client.START_RENDER.broadcast { it.invoke() } }
    END.register { Client.END_RENDER.broadcast { it.invoke() } }
    START_WORLD_TICK.register { l -> Client.START_WORLD_TICK.broadcast { it.invoke(l) } }
    END_WORLD_TICK.register { l -> Client.END_WORLD_TICK.broadcast { it.invoke(l) } }
    START_CLIENT_TICK.register { c -> Client.START_CLIENT_TICK.broadcast { it.invoke(c) } }
    END_CLIENT_TICK.register { c -> Client.END_CLIENT_TICK.broadcast { it.invoke(c) } }
}