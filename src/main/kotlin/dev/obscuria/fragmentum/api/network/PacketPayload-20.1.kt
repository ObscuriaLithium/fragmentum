package dev.obscuria.fragmentum.api.network

//? 1.20.1 {
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Player
import java.util.function.BiConsumer
import java.util.function.Function

interface PacketPayload {

	fun info(): Info<*>

	data class Info<T : PacketPayload>(
		val type: Class<T>,
		val encoder: BiConsumer<T, FriendlyByteBuf>,
		val decoder: Function<FriendlyByteBuf, T>,
		val handler: BiConsumer<Player, T>
	)
}//?}
