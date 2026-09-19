package dev.obscuria.fragmentum.api.network

interface PayloadRegistrar {

	fun allowClientOnly()

	fun allowServerOnly()

	fun <T : PacketPayload> registerClientbound(info: PacketPayload.Info<T>)

	fun <T : PacketPayload> registerServerbound(info: PacketPayload.Info<T>)
}
