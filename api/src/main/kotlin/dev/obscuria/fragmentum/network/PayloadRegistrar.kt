@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.network

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import java.util.function.BiConsumer
import java.util.function.Function

interface PayloadRegistrar {

    fun allowClientOnly()

    fun allowServerOnly()

    fun <T : Any> registerClientbound(
        clazz: Class<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        handler: BiConsumer<Player, T>
    )

    fun <T : Any> registerServerbound(
        clazz: Class<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        handler: BiConsumer<ServerPlayer, T>
    )
}