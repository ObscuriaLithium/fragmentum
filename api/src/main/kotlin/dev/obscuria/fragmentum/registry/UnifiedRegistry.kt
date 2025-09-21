@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.registry

import com.mojang.serialization.Codec
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

interface UnifiedRegistry<T> {

    val key: ResourceKey<out Registry<T>>

    val name: ResourceLocation

    val byNameCodec: Codec<T>

    val holderByNameCodec: Codec<Holder<T>>

    fun keySet(): Set<ResourceLocation>

    fun entrySet(): Set<Map.Entry<ResourceKey<T>, T>>

    fun containsKey(id: ResourceLocation): Boolean

    fun get(id: ResourceLocation): T?

    fun getHolder(id: ResourceLocation): Holder.Reference<T>?

    fun getKey(holder: Holder<T>): ResourceLocation?

    fun getKey(value: T & Any): ResourceLocation?
}