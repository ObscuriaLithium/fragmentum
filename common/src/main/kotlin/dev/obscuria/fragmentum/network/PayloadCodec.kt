@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.network

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.mojang.serialization.Codec
import com.mojang.serialization.JsonOps
import io.netty.handler.codec.DecoderException
import io.netty.handler.codec.EncoderException
import net.minecraft.Util
import net.minecraft.core.RegistryAccess
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.RegistryOps
import net.minecraft.util.GsonHelper

sealed interface PayloadCodec<T> {

    fun write(buf: FriendlyByteBuf, value: T & Any)

    fun read(buf: FriendlyByteBuf): T

    private data class RegistryFriendly<T>(
        val codec: Codec<T>,
        val registry: () -> RegistryAccess
    ) : PayloadCodec<T> {

        override fun write(buf: FriendlyByteBuf, value: T & Any) {
            buf.writeRegistryFriendly(codec, registry(), value)
        }

        override fun read(buf: FriendlyByteBuf): T {
            return buf.readRegistryFriendly(codec, registry())
        }
    }

    companion object {

        private const val MAX_LENGTH = 1048576
        private val GSON: Gson = Gson()

        fun <T> registryFriendly(codec: Codec<T>, registry: () -> RegistryAccess): PayloadCodec<T> {
            return RegistryFriendly(codec, registry)
        }

        fun <T> FriendlyByteBuf.writeRegistryFriendly(codec: Codec<T>, registry: RegistryAccess, value: T) {
            val registryOps = RegistryOps.create(JsonOps.INSTANCE, registry)
            codec.encodeStart(registryOps, value).let {
                this.writeUtf(GSON.toJson(Util.getOrThrow(it) { exception ->
                    EncoderException("Failed to encode: $exception ${value.toString()}")
                }), MAX_LENGTH)
            }
        }

        fun <T> FriendlyByteBuf.readRegistryFriendly(codec: Codec<T>, registry: RegistryAccess): T {
            val element = GsonHelper.fromJson(GSON, this.readUtf(MAX_LENGTH), JsonElement::class.java)
            val registryOps = RegistryOps.create(JsonOps.INSTANCE, registry)
            return codec.parse(registryOps, element).let {
                Util.getOrThrow(it) { exception ->
                    DecoderException("Failed to decode json: $exception")
                }
            }
        }
    }
}