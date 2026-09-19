package dev.obscuria.fragmentum.platform.forge

//? forge {
import dev.obscuria.fragmentum.api.network.PacketPayload
import dev.obscuria.fragmentum.api.network.PayloadRegistrar
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal data class ForgePayloadRegistrar(val modId: String) : PayloadRegistrar {

	override fun allowClientOnly() {
		ForgeNetworking.allowClientOnly(modId)
	}

	override fun allowServerOnly() {
		ForgeNetworking.allowServerOnly(modId)
	}

	override fun <T : PacketPayload> registerClientbound(info: PacketPayload.Info<T>) {
		ForgeNetworking.registerClientbound(modId, info)
	}

	override fun <T : PacketPayload> registerServerbound(info: PacketPayload.Info<T>) {
		ForgeNetworking.registerServerbound(modId, info)
	}
}
//?}
