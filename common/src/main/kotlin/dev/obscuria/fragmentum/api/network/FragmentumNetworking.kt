package dev.obscuria.fragmentum.api.network

import dev.obscuria.fragmentum.api.Fragmentum
import net.minecraft.core.BlockPos
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity

object FragmentumNetworking
{
    fun registrar(modId: String): PayloadRegistrar =
        Fragmentum.SERVICES.network().payloadRegistrar(modId)

    fun <T : Any> reply(payload: T) =
        Fragmentum.SERVICES.network().reply(payload)

    fun <T : Any> sendTo(player: ServerPlayer, payload: T) =
        Fragmentum.SERVICES.network().sendTo(player, payload)

    fun <T : Any> sendToTracking(world: ServerLevel, pos: BlockPos, payload: T) =
        Fragmentum.SERVICES.network().sendToTracking(world, pos, payload)

    fun <T : Any> sendToTracking(entity: Entity, payload: T) =
        Fragmentum.SERVICES.network().sendToTracking(entity, payload)

    fun <T : Any> sendToAll(server: MinecraftServer, payload: T) =
        Fragmentum.SERVICES.network().sendToAll(server, payload)

    fun <T : Any> sendToServer(payload: T) =
        Fragmentum.SERVICES.network().sendToServer(payload)
}