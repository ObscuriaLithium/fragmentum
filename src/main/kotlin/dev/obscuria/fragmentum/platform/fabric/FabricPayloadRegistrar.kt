package dev.obscuria.fragmentum.platform.fabric

//? fabric {
/*import dev.obscuria.fragmentum.api.network.PacketPayload
import dev.obscuria.fragmentum.api.network.PayloadRegistrar
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal data class FabricPayloadRegistrar(val modId: String) : PayloadRegistrar {

	override fun allowClientOnly() {}

	override fun allowServerOnly() {}

	override fun <T : PacketPayload> registerClientbound(info: PacketPayload.Info<T>) {
		FabricNetworking.registerClientbound(modId, info)
	}

	override fun <T : PacketPayload> registerServerbound(info: PacketPayload.Info<T>) {
		FabricNetworking.registerServerbound(modId, info)
	}
}
*///?}
