@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType", "UnusedReceiverParameter")

package dev.obscuria.fragmentum.network

import com.mojang.serialization.Codec
import net.minecraft.core.RegistryAccess
import net.minecraft.network.FriendlyByteBuf

interface PayloadCodec<T> {

    fun write(buf: FriendlyByteBuf, value: T & Any)

    fun read(buf: FriendlyByteBuf): T

    companion object {

        fun <T> registryFriendly(codec: Codec<T>, registry: () -> RegistryAccess): PayloadCodec<T> = error("API stub")
    }
}