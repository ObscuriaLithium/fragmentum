package dev.obscuria.fragmentum.platform.fabric

//? fabric && >=1.21.1 {
/*import dev.obscuria.fragmentum.Fragmentum
import dev.obscuria.fragmentum.api.network.PacketPayload
import dev.obscuria.fragmentum.api.network.PayloadRegistrar
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.PlayerLookup
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.core.BlockPos
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal object FabricNetworking {

	var clientReplySender: PacketSender? = null
	var serverReplySender: PacketSender? = null

	fun registrar(modId: String): PayloadRegistrar {
		return FabricPayloadRegistrar(modId)
	}

	fun <T : PacketPayload> reply(payload: T) {
		clientReplySender?.sendPacket(payload)
		serverReplySender?.sendPacket(payload)
	}

	fun <T : PacketPayload> sendTo(player: ServerPlayer, payload: T) {
		ServerPlayNetworking.send(player, payload)
	}

	fun <T : PacketPayload> sendToAllTracking(level: ServerLevel, pos: BlockPos, payload: T) {
		PlayerLookup.tracking(level, pos).forEach {
			sendTo(it, payload)
		}
	}

	fun <T : PacketPayload> sendToAllTracking(entity: Entity, payload: T) {
		PlayerLookup.tracking(entity).forEach {
			sendTo(it, payload)
		}
	}

	fun <T : PacketPayload> sendToAll(server: MinecraftServer, payload: T) {
		PlayerLookup.all(server).forEach {
			sendTo(it, payload)
		}
	}

	fun <T : PacketPayload> sendToServer(payload: T) {
		ClientPlayNetworking.send(payload)
	}

	fun <T : PacketPayload> registerClientbound(modId: String, info: PacketPayload.Info<T>) {
		PayloadTypeRegistry.playS2C().register(info.payloadType, info.codec)
		if (Fragmentum.PLATFORM.isDedicatedServer()) return
		ClientPlayNetworking.registerGlobalReceiver(info.payloadType) { payload, context ->
			clientReplySender = context.responseSender()
			info.handler.accept(context.player(), payload)
			clientReplySender = null
		}
	}

	fun <T : PacketPayload> registerServerbound(modId: String, info: PacketPayload.Info<T>) {
		PayloadTypeRegistry.playC2S().register(info.payloadType, info.codec)
		ServerPlayNetworking.registerGlobalReceiver(info.payloadType) { payload, context ->
			serverReplySender = context!!.responseSender()
			info.handler.accept(context.player(), payload)
			serverReplySender = null
		}
	}
}
*///?}
