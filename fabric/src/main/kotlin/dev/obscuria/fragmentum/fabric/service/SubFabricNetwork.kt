package dev.obscuria.fragmentum.fabric.service

import dev.obscuria.fragmentum.api.network.PayloadRegistrar
import dev.obscuria.fragmentum.api.service.NetworkService
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.PlayerLookup
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.core.BlockPos
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity

internal class SubFabricNetwork : NetworkService
{
    override fun payloadRegistrar(modId: String): PayloadRegistrar = FabricPayloadRegistrar(modId)

    override fun <T : Any> reply(payload: T)
    {
        check(clientReplySender == null && serverReplySender == null) { "No context to reply to" }
        clientReplySender?.sendPacket(FabricPayloadRegistrar.clientbound(payload))
        serverReplySender?.sendPacket(FabricPayloadRegistrar.clientbound(payload))
    }

    override fun <T : Any> sendTo(player: ServerPlayer, payload: T)
    {
        ServerPlayNetworking.send(player, FabricPayloadRegistrar.clientbound(payload))
    }

    override fun <T : Any> sendToTracking(world: ServerLevel, pos: BlockPos, payload: T)
    {
        PlayerLookup.tracking(world, pos).forEach {
            ServerPlayNetworking.send(it, FabricPayloadRegistrar.clientbound(payload))
        }
    }

    override fun <T : Any> sendToTracking(entity: Entity, payload: T)
    {
        PlayerLookup.tracking(entity).forEach {
            ServerPlayNetworking.send(it, FabricPayloadRegistrar.clientbound(payload))
        }
    }

    override fun <T : Any> sendToAll(server: MinecraftServer, payload: T)
    {
        PlayerLookup.all(server).forEach {
            ServerPlayNetworking.send(it, FabricPayloadRegistrar.clientbound(payload))
        }
    }

    override fun <T : Any> sendToServer(payload: T)
    {
        ClientPlayNetworking.send(FabricPayloadRegistrar.serverbound(payload))
    }

    companion object
    {
        val INSTANCE = SubFabricNetwork()
        var clientReplySender: PacketSender? = null
        var serverReplySender: PacketSender? = null
    }
}