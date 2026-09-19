package dev.obscuria.fragmentum.platform.neoforge

//? neoforge {
/*import dev.obscuria.fragmentum.api.network.PacketPayload
import dev.obscuria.fragmentum.api.network.PayloadRegistrar
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal data class NeoforgePayloadRegistrar(val modId: String) : PayloadRegistrar {

	override fun allowClientOnly() {
		NeoforgeNetworking.allowClientOnly(modId)
	}

	override fun allowServerOnly() {
		NeoforgeNetworking.allowServerOnly(modId)
	}

	override fun <T : PacketPayload> registerClientbound(info: PacketPayload.Info<T>) {
		NeoforgeNetworking.registerClientbound(modId, info)
	}

	override fun <T : PacketPayload> registerServerbound(info: PacketPayload.Info<T>) {
		NeoforgeNetworking.registerServerbound(modId, info)
	}
}
*///?}
