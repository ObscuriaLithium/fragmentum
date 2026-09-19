package dev.obscuria.fragmentum.platform.neoforge

//? neoforge {
/*import dev.obscuria.fragmentum.api.network.PacketPayload
import dev.obscuria.fragmentum.api.network.PayloadRegistrar
import net.minecraft.core.BlockPos
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.ChunkPos
import net.neoforged.neoforge.network.PacketDistributor
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal object NeoforgeNetworking {

	val clientOnlyMods = mutableSetOf<String>()
	val serverOnlyMods = mutableSetOf<String>()
	var replyConsumer: ((PacketPayload) -> Unit)? = null

	fun registrar(modId: String): PayloadRegistrar {
		return NeoforgePayloadRegistrar(modId)
	}

	fun <T : PacketPayload> reply(payload: T) {
		checkNotNull(replyConsumer) { "No context to reply to" }
		replyConsumer?.invoke(payload)
	}

	fun <T : PacketPayload> sendTo(player: ServerPlayer, payload: T) {
		PacketDistributor.sendToPlayer(player, payload)
	}

	fun <T : PacketPayload> sendToAllTracking(level: ServerLevel, pos: BlockPos, payload: T) {
		PacketDistributor.sendToPlayersTrackingChunk(level, ChunkPos(pos), payload)
	}

	fun <T : PacketPayload> sendToAllTracking(entity: Entity, payload: T) {
		PacketDistributor.sendToPlayersTrackingEntity(entity, payload)
	}

	fun <T : PacketPayload> sendToAll(server: MinecraftServer?, payload: T) {
		PacketDistributor.sendToAllPlayers(payload)
	}

	fun <T : PacketPayload> sendToServer(payload: T) {
		PacketDistributor.sendToServer(payload)
	}

	fun allowClientOnly(modId: String) {
		clientOnlyMods.add(modId)
	}

	fun allowServerOnly(modId: String) {
		serverOnlyMods.add(modId)
	}

	fun <T : PacketPayload> registerClientbound(modId: String, info: PacketPayload.Info<T>) {
		NeoforgeEntrypoint.addListener<RegisterPayloadHandlersEvent>(modId) {
			val registrar = it.registrar("1")
			if (clientOnlyMods.contains(modId)) registrar.optional()
			registrar.playToClient(info.payloadType, info.codec) { payload, context ->
				context.enqueueWork {
					replyConsumer = context::reply
					info.handler.accept(context.player(), payload)
					replyConsumer = null
				}
			}
		}
	}

	fun <T : PacketPayload> registerServerbound(modId: String, info: PacketPayload.Info<T>) {
		NeoforgeEntrypoint.addListener<RegisterPayloadHandlersEvent>(modId) {
			val registrar = it.registrar("1")
			if (serverOnlyMods.contains(modId)) registrar.optional()
			registrar.playToServer(info.payloadType, info.codec) { payload, context ->
				context.enqueueWork {
					replyConsumer = context::reply
					info.handler.accept(context.player(), payload)
					replyConsumer = null
				}
			}
		}
	}
}
*///?}
