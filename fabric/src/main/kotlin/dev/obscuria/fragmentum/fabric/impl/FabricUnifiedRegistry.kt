package dev.obscuria.fragmentum.fabric.impl

import com.mojang.serialization.Codec
import dev.obscuria.fragmentum.registry.UnifiedRegistry
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

internal data class FabricUnifiedRegistry<T>(val source: Registry<T>) : UnifiedRegistry<T> {

    override val key: ResourceKey<out Registry<T>> get() = source.key()

    override val name: ResourceLocation get() = source.key().registry()

    override val byNameCodec: Codec<T> get() = source.byNameCodec()

    override val holderByNameCodec: Codec<Holder<T>> get() = source.holderByNameCodec()

    override fun keySet(): Set<ResourceLocation> {
        return source.keySet()
    }

    override fun entrySet(): Set<Map.Entry<ResourceKey<T>, T>> {
        return source.entrySet()
    }

    override fun containsKey(id: ResourceLocation): Boolean {
        return source.containsKey(id)
    }

    override fun get(id: ResourceLocation): T? {
        return source.get(id)
    }

    override fun getHolder(id: ResourceLocation): Holder.Reference<T>? {
        return source.getHolder(ResourceKey.create(source.key(), id)).orElse(null)
    }

    override fun getKey(holder: Holder<T>): ResourceLocation? {
        return getKey(holder.unwrap().right().get())
    }

    override fun getKey(value: T & Any): ResourceLocation? {
        return source.getKey(value)
    }
}