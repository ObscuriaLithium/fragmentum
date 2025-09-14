package dev.obscuria.fragmentum.fabric.service

import com.google.common.collect.Maps
import dev.obscuria.fragmentum.api.Fragmentum
import dev.obscuria.fragmentum.api.network.PayloadRegistrar
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.FabricPacket
import net.fabricmc.fabric.api.networking.v1.PacketType
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import java.util.*
import java.util.function.BiConsumer
import java.util.function.Function

internal data class FabricPayloadRegistrar(val modId: String) : PayloadRegistrar {

    override fun allowClientOnly() {}

    override fun allowServerOnly() {}

    override fun <T : Any> registerClientbound(
        clazz: Class<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        handler: BiConsumer<Player, T>
    ) {
        val id = ResourceLocation(modId, "clientbound_" + clazz.simpleName.lowercase(Locale.getDefault()))
        val type = PacketType.create(id) { ClientboundWrapper(clazz, decoder.apply(it)) }
        clientboundRegistrations[clazz] = ClientboundRegistration(type, encoder)

        if (Fragmentum.PLATFORM.isDedicatedServer()) return
        ClientPlayNetworking.registerGlobalReceiver(type) { packet, player, responseSender ->
            SubFabricNetwork.clientReplySender = responseSender
            handler.accept(player, packet.payload)
            SubFabricNetwork.clientReplySender = null
        }
    }

    override fun <T : Any> registerServerbound(
        clazz: Class<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        handler: BiConsumer<ServerPlayer, T>
    ) {
        val id = ResourceLocation(modId, "serverbound_" + clazz.simpleName.lowercase(Locale.getDefault()))
        val type = PacketType.create(id) { ServerboundWrapper(clazz, decoder.apply(it)) }
        serverboundRegistrations[clazz] = ServerboundRegistration(type, encoder)

        ServerPlayNetworking.registerGlobalReceiver(type) { packet, player, responseSender ->
            SubFabricNetwork.serverReplySender = responseSender
            handler.accept(player, packet.payload)
            SubFabricNetwork.serverReplySender = null
        }
    }

    @Suppress("UNCHECKED_CAST")
    data class ClientboundRegistration<T>(
        val type: PacketType<ClientboundWrapper<T>>,
        val encoder: BiConsumer<T, FriendlyByteBuf>
    ) {
        fun encode(buf: FriendlyByteBuf, payload: Any) = encoder.accept(payload as T, buf)
    }

    @Suppress("UNCHECKED_CAST")
    data class ServerboundRegistration<T>(
        val type: PacketType<ServerboundWrapper<T>>,
        val encoder: BiConsumer<T, FriendlyByteBuf>
    ) {
        fun encode(buf: FriendlyByteBuf, payload: Any) = encoder.accept(payload as T, buf)
    }

    data class ClientboundWrapper<T>(val clazz: Class<*>, val payload: T & Any) : FabricPacket {
        override fun write(buf: FriendlyByteBuf) = clientboundRegistrations[clazz]!!.encode(buf, this.payload)

        override fun getType(): PacketType<*> = clientboundRegistrations[clazz]!!.type
    }

    data class ServerboundWrapper<T>(val clazz: Class<*>, val payload: T & Any) : FabricPacket {
        override fun write(buf: FriendlyByteBuf) = serverboundRegistrations[clazz]!!.encode(buf, this.payload)

        override fun getType(): PacketType<*> = serverboundRegistrations[clazz]!!.type
    }

    companion object {

        val clientboundRegistrations: MutableMap<Class<*>, ClientboundRegistration<*>> = Maps.newHashMap()
        val serverboundRegistrations: MutableMap<Class<*>, ServerboundRegistration<*>> = Maps.newHashMap()

        fun clientbound(payload: Any): FabricPacket {
            require(clientboundRegistrations.containsKey(payload.javaClass)) {
                "No clientbound packet registered for " + payload.javaClass.simpleName
            }
            return ClientboundWrapper(payload.javaClass, payload)
        }

        fun serverbound(payload: Any): FabricPacket {
            require(serverboundRegistrations.containsKey(payload.javaClass)) {
                "No serverbound packet registered for " + payload.javaClass.simpleName
            }
            return ServerboundWrapper(payload.javaClass, payload)
        }
    }
}