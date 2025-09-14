package dev.obscuria.fragmentum.api.registry

import com.mojang.serialization.Codec
import net.minecraft.core.Registry
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import java.util.function.Supplier

interface Registrar {

    fun <T, V : T> register(
        registryKey: ResourceKey<out Registry<T>>,
        id: ResourceLocation,
        supplier: Supplier<V>
    ): Deferred<T, V>

    fun <T, V : T> register(
        registry: Registry<T>,
        id: ResourceLocation,
        supplier: Supplier<V>
    ): Deferred<T, V>

    fun <T : Item> registerItem(
        id: ResourceLocation,
        supplier: Supplier<T>
    ): DeferredItem<T>

    fun <T : Block> registerBlock(
        id: ResourceLocation,
        supplier: Supplier<T>
    ): DeferredBlock<T>

    fun registerAttribute(
        id: ResourceLocation,
        supplier: Supplier<Attribute>
    ): DeferredAttribute

    fun <T : Entity> registerEntity(
        id: ResourceLocation,
        supplier: Supplier<EntityType<T>>
    ): DeferredEntity<T>

    fun <T : BlockEntity> registerBlockEntity(
        id: ResourceLocation,
        supplier: Supplier<BlockEntityType<T>>
    ): DeferredBlockEntity<T>

    fun <T : MobEffect> registerMobEffect(
        id: ResourceLocation,
        supplier: Supplier<T>
    ): DeferredMobEffect<T>

    fun <T : ParticleOptions> registerParticle(
        id: ResourceLocation,
        supplier: Supplier<ParticleType<T>>
    ): DeferredParticle<T>

    fun <T> newRegistry(
        key: ResourceKey<Registry<T>>
    ): UnifiedRegistry<T>

    fun <T> newDataRegistry(
        key: ResourceKey<Registry<T>>,
        codec: Supplier<Codec<T>>
    )

    fun <T> newSyncedDataRegistry(
        key: ResourceKey<Registry<T>>,
        codec: Supplier<Codec<T>>
    )

    fun <T> newSyncedDataRegistry(
        key: ResourceKey<Registry<T>>,
        codec: Supplier<Codec<T>>,
        networkCodec: Supplier<Codec<T>>
    )

    fun registerAttributes(type: DeferredEntity<out LivingEntity>, builder: AttributeSupplier.Builder)
}