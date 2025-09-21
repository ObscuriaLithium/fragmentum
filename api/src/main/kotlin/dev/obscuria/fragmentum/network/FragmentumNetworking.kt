@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.network

import net.minecraft.core.BlockPos
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity

object FragmentumNetworking {

    fun registrar(modId: String): PayloadRegistrar = error("API stub")

    fun <T : Any> reply(payload: T): Unit = error("API stub")

    fun <T : Any> sendTo(player: ServerPlayer, payload: T): Unit = error("API stub")

    fun <T : Any> sendToTracking(world: ServerLevel, pos: BlockPos, payload: T): Unit = error("API stub")

    fun <T : Any> sendToTracking(entity: Entity, payload: T): Unit = error("API stub")

    fun <T : Any> sendToAll(server: MinecraftServer, payload: T): Unit = error("API stub")

    fun <T : Any> sendToServer(payload: T): Unit = error("API stub")
}