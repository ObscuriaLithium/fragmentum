package dev.obscuria.fragmentum.fabric.service

import com.mojang.serialization.Codec
import dev.obscuria.fragmentum.api.registry.UnifiedRegistry
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

@JvmRecord
internal data class FabricUnifiedRegistry<T>(val source: Registry<T>) : UnifiedRegistry<T>
{
    override val key: ResourceKey<out Registry<T>>
        get() = source.key()

    override val name: ResourceLocation
        get() = source.key().registry()

    override val byNameCodec: Codec<T>
        get() = source.byNameCodec()

    override val holderByNameCodec: Codec<Holder<T>>
        get() = source.holderByNameCodec()

    override fun keySet(): Set<ResourceLocation> = source.keySet()

    override fun entrySet(): Set<Map.Entry<ResourceKey<T>, T>> = source.entrySet()

    override fun containsKey(id: ResourceLocation): Boolean = source.containsKey(id)

    override fun get(id: ResourceLocation): T? = source.get(id)

    override fun getHolder(id: ResourceLocation): Holder.Reference<T>? =
        source.getHolder(ResourceKey.create(source.key(), id)).orElse(null)

    override fun getKey(holder: Holder<T>): ResourceLocation? = getKey(holder.unwrap().right().get())

    override fun getKey(value: T & Any): ResourceLocation? = source.getKey(value)
}