@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.client

import dev.obscuria.fragmentum.tools.event.Event
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientLevel

object FragmentumClientEvents {

    val START_RENDER: Event<WorldRender> = Event()
    val END_RENDER: Event<WorldRender> = Event()

    val START_WORLD_TICK: Event<WorldTick> = Event()
    val END_WORLD_TICK: Event<WorldTick> = Event()

    val START_CLIENT_TICK: Event<ClientTick> = Event()
    val END_CLIENT_TICK: Event<ClientTick> = Event()

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