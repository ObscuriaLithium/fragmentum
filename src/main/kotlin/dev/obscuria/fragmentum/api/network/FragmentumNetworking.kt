package dev.obscuria.fragmentum.api.network

//? fabric
//import dev.obscuria.fragmentum.platform.fabric.FabricNetworking as PlatformNetworking
//? forge
import dev.obscuria.fragmentum.platform.forge.ForgeNetworking as PlatformNetworking
//? neoforge
//import dev.obscuria.fragmentum.platform.neoforge.NeoforgeNetworking as PlatformNetworking

import net.minecraft.core.BlockPos
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity

object FragmentumNetworking {

	fun registrar(modId: String): PayloadRegistrar {
		return PlatformNetworking.registrar(modId)
	}

	fun <T : PacketPayload> reply(payload: T) {
		PlatformNetworking.reply(payload)
	}

	fun <T : PacketPayload> sendTo(player: ServerPlayer, payload: T) {
		PlatformNetworking.sendTo(player, payload)
	}

	fun <T : PacketPayload> sendToAllTracking(level: ServerLevel, pos: BlockPos, payload: T) {
		PlatformNetworking.sendToAllTracking(level, pos, payload)
	}

	fun <T : PacketPayload> sendToAllTracking(entity: Entity, payload: T) {
		PlatformNetworking.sendToAllTracking(entity, payload)
	}

	fun <T : PacketPayload> sendToAll(server: MinecraftServer, payload: T) {
		PlatformNetworking.sendToAll(server, payload)
	}

	fun <T : PacketPayload> sendToServer(payload: T) {
		PlatformNetworking.sendToServer(payload)
	}
}
