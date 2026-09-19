package dev.obscuria.fragmentum.api.network

//? >=1.21.1 {
/*import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.entity.player.Player
import org.apache.logging.log4j.util.BiConsumer

interface PacketPayload : CustomPacketPayload {

	fun info(): Info<*>

	override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
		return info().payloadType
	}

	data class Info<T : PacketPayload>(
		val type: Class<T>,
		val payloadType : CustomPacketPayload.Type<T>,
		val codec: StreamCodec<in RegistryFriendlyByteBuf, T>,
		val handler: BiConsumer<Player, T>
	)
}
*///?}
