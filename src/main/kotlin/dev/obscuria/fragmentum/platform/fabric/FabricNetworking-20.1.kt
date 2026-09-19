package dev.obscuria.fragmentum.platform.fabric

//? fabric && 1.20.1 {
/*import dev.obscuria.fragmentum.Fragmentum
import dev.obscuria.fragmentum.api.network.PacketPayload
import dev.obscuria.fragmentum.api.network.PayloadRegistrar
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.*
import net.minecraft.core.BlockPos
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import org.jetbrains.annotations.ApiStatus
import java.util.*

@ApiStatus.Internal
internal object FabricNetworking {
	val clientboundRegistrations = mutableMapOf<PacketPayload.Info<*>, Registration<*>>()
	val serverboundRegistrations = mutableMapOf<PacketPayload.Info<*>, Registration<*>>()
	var clientReplySender: PacketSender? = null
	var serverReplySender: PacketSender? = null

	fun registrar(modId: String): PayloadRegistrar {
		return FabricPayloadRegistrar(modId)
	}

	fun <T : PacketPayload> reply(payload: T) {
		clientReplySender?.sendPacket(serverbound(payload))
		serverReplySender?.sendPacket(clientbound(payload))
	}

	fun <T : PacketPayload> sendTo(player: ServerPlayer, payload: T) {
		ServerPlayNetworking.send(player, clientbound(payload))
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
		ClientPlayNetworking.send(serverbound(payload))
	}

	fun <T : PacketPayload> registerClientbound(modId: String, info: PacketPayload.Info<T>) {
		val payloadName = info.type.simpleName.lowercase(Locale.ROOT)
		val id = Fragmentum.id(modId, "clientbound_${payloadName}")
		val type: PacketType<Wrapper<T>> = PacketType.create(id) {
			Wrapper.clientbound(info.decoder.apply(it))
		}
		clientboundRegistrations[info] = Registration(type, info)
		if (Fragmentum.PLATFORM.isDedicatedServer()) return
		ClientPlayNetworking.registerGlobalReceiver(type) { packet, player, sender ->
			clientReplySender = sender
			info.handler.accept(player, packet.payload)
			clientReplySender = null
		}
	}

	fun <T : PacketPayload> registerServerbound(modId: String, info: PacketPayload.Info<T>) {
		val payloadName = info.type.simpleName.lowercase(Locale.ROOT)
		val id = Fragmentum.id(modId, "serverbound_${payloadName}")
		val type: PacketType<Wrapper<T>> = PacketType.create(id) {
			Wrapper.serverbound(info.decoder.apply(it))
		}
		serverboundRegistrations[info] = Registration(type, info)
		ServerPlayNetworking.registerGlobalReceiver(type) { packet, player, sender ->
			clientReplySender = sender
			info.handler.accept(player, packet.payload)
			clientReplySender = null
		}
	}

	fun clientbound(payload: PacketPayload): FabricPacket {
		Objects.requireNonNull(clientboundRegistrations[payload.info()])
		return Wrapper.clientbound(payload)
	}

	fun serverbound(payload: PacketPayload): FabricPacket {
		Objects.requireNonNull(serverboundRegistrations[payload.info()])
		return Wrapper.serverbound(payload)
	}

	data class Registration<T : PacketPayload>(
		val type: PacketType<Wrapper<T>>,
		val info: PacketPayload.Info<T>
	) {

		@Suppress("UNCHECKED_CAST")
		fun encode(buf: FriendlyByteBuf, payload: Any) {
			info.encoder.accept(payload as T, buf)
		}
	}

	data class Wrapper<T : PacketPayload>(
        val source: MutableMap<PacketPayload.Info<*>, Registration<*>>,
        val payload: T
	) : FabricPacket {

		override fun write(buf: FriendlyByteBuf) {
			source[payload.info()]?.encode(buf, payload)
		}

		override fun getType(): PacketType<*>? {
			return source[payload.info()]?.type
		}

		companion object {

			fun <T : PacketPayload> clientbound(payload: T): Wrapper<T> {
				return Wrapper(clientboundRegistrations, payload)
			}

			fun <T : PacketPayload> serverbound(payload: T): Wrapper<T> {
				return Wrapper(serverboundRegistrations, payload)
			}
		}
	}
}
*///?}
