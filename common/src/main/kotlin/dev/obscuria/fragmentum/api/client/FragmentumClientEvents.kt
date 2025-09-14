package dev.obscuria.fragmentum.api.client

import dev.obscuria.fragmentum.api.tools.event.Event
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientLevel

object FragmentumClientEvents {

    val START_RENDER: Event<WorldRender> = Event.create()
    val END_RENDER: Event<WorldRender> = Event.create()

    val START_WORLD_TICK: Event<WorldTick> = Event.create()
    val END_WORLD_TICK: Event<WorldTick> = Event.create()

    val START_CLIENT_TICK: Event<ClientTick> = Event.create()
    val END_CLIENT_TICK: Event<ClientTick> = Event.create()

    fun interface WorldRender {
        fun invoke()
    }

    fun interface WorldTick {
        fun invoke(level: ClientLevel?)
    }

    fun interface ClientTick {
        fun invoke(minecraft: Minecraft?)
    }
}