package dev.obscuria.fragmentum.api.service

import dev.obscuria.fragmentum.api.network.PayloadRegistrar
import net.minecraft.core.BlockPos
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity

interface NetworkService
{
    fun payloadRegistrar(modId: String) : PayloadRegistrar

    fun <T : Any> reply(payload: T)

    fun <T : Any> sendTo(player: ServerPlayer, payload: T)

    fun <T : Any> sendToTracking(world: ServerLevel, pos: BlockPos, payload: T)

    fun <T : Any> sendToTracking(entity: Entity, payload: T)

    fun <T : Any> sendToAll(server: MinecraftServer, payload: T)

    fun <T : Any> sendToServer(payload: T)
}