package dev.obscuria.fragmentum.platform.forge

//? forge {
import com.google.common.base.Predicates
import com.google.common.base.Suppliers
import dev.obscuria.fragmentum.Fragmentum
import dev.obscuria.fragmentum.api.network.PacketPayload
import dev.obscuria.fragmentum.api.network.PayloadRegistrar
import net.minecraft.core.BlockPos
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraftforge.network.NetworkDirection
import net.minecraftforge.network.NetworkEvent
import net.minecraftforge.network.NetworkRegistry
import net.minecraftforge.network.PacketDistributor
import net.minecraftforge.network.simple.SimpleChannel
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal object ForgeNetworking {

	val clientOnlyMods = mutableSetOf<String>()
	val serverOnlyMods = mutableSetOf<String>()
	val channelByMod = mutableMapOf<String, SimpleChannel>()
	val channelByInfo = mutableMapOf<PacketPayload.Info<*>, SimpleChannel>()
	val idByMod = mutableMapOf<String, Int>()
	var replyContext: NetworkEvent.Context? = null

	fun registrar(modId: String): PayloadRegistrar {
		return ForgePayloadRegistrar(modId)
	}

	fun <T : PacketPayload> reply(payload: T) {
		checkNotNull(replyContext) { "No context to reply to" }
		channelFor(payload)?.reply(payload, replyContext)
	}

	fun <T : PacketPayload> sendTo(player: ServerPlayer, payload: T) {
		channelFor(payload)?.send(PacketDistributor.PLAYER.with { player }, payload)
	}

	fun <T : PacketPayload> sendToAllTracking(level: ServerLevel, pos: BlockPos, payload: T) {
		channelFor(payload)?.send(PacketDistributor.TRACKING_CHUNK.with { level.getChunkAt(pos) }, payload)
	}

	fun <T : PacketPayload> sendToAllTracking(entity: Entity, payload: T) {
		channelFor(payload)?.send(PacketDistributor.TRACKING_ENTITY.with { entity }, payload)
	}

	fun <T : PacketPayload> sendToAll(server: MinecraftServer, payload: T) {
		channelFor(payload)?.send(PacketDistributor.ALL.noArg(), payload)
	}

	fun <T : PacketPayload> sendToServer(payload: T) {
		channelFor(payload)?.send(PacketDistributor.SERVER.noArg(), payload)
	}

	fun allowClientOnly(modId: String) {
		clientOnlyMods.add(modId)
	}

	fun allowServerOnly(modId: String) {
		serverOnlyMods.add(modId)
	}

	fun <T : PacketPayload> registerClientbound(modId: String, info: PacketPayload.Info<T>) {
		val channel = getOrCreateChannel(modId)
		channelByInfo[info] = channel
		channel.messageBuilder(info.type, nextIdFor(modId), NetworkDirection.PLAY_TO_CLIENT)
			.encoder(info.encoder).decoder(info.decoder)
			.consumerMainThread { payload, context ->
				replyContext = context.get()
				net.minecraft.client.Minecraft.getInstance().player?.let {
					info.handler.accept(it, payload)
				}
				replyContext = null
			}.add()
	}

	fun <T : PacketPayload> registerServerbound(modId: String, info: PacketPayload.Info<T>) {
		val channel = getOrCreateChannel(modId)
		channelByInfo[info] = channel
		channel.messageBuilder(info.type, nextIdFor(modId), NetworkDirection.PLAY_TO_SERVER)
			.encoder(info.encoder).decoder(info.decoder)
			.consumerMainThread { payload, context ->
				replyContext = context.get()
				context.get().sender?.let {
					info.handler.accept(it, payload)
				}
				replyContext = null
			}.add()
	}

	fun getOrCreateChannel(modId: String): SimpleChannel {
		return channelByMod.computeIfAbsent(modId) {
			NetworkRegistry.ChannelBuilder
				.named(Fragmentum.id(it, "fragmentum"))
				.networkProtocolVersion(Suppliers.ofInstance("1"))
				.clientAcceptedVersions(Predicates.alwaysTrue())
				.serverAcceptedVersions(Predicates.alwaysTrue())
				.simpleChannel()
		}
	}

	fun channelFor(payload: PacketPayload): SimpleChannel? {
		return channelByInfo[payload.info()]
	}

	fun nextIdFor(modId: String): Int {
		val next = (idByMod[modId] ?: -1) + 1
		idByMod[modId] = next
		return next
	}
}
//?}
