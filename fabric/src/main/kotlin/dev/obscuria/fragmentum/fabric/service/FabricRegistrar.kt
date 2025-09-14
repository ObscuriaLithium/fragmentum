package dev.obscuria.fragmentum.fabric.service

import com.mojang.serialization.Codec
import dev.obscuria.fragmentum.api.registry.*
import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.fabricmc.fabric.api.event.registry.RegistryAttribute
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.core.Registry
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.registries.BuiltInRegistries as Regs
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

internal data class FabricRegistrar(val modId: String) : Registrar {

    @Suppress("UNCHECKED_CAST")
    override fun <T, V : T> register(
        registryKey: ResourceKey<out Registry<T>>,
        id: ResourceLocation,
        supplier: Supplier<V>
    ): Deferred<T, V> {
        return register(Regs.REGISTRY[registryKey.location()] as Registry<T>, id, supplier)
    }

    override fun <T, V : T> register(
        registry: Registry<T>,
        id: ResourceLocation,
        supplier: Supplier<V>
    ): Deferred<T, V> {
        val holder = Registry.registerForHolder(registry, id, supplier.get()!!)
        return Deferred { holder }
    }

    override fun <T : Item> registerItem(
        id: ResourceLocation,
        supplier: Supplier<T>
    ): DeferredItem<T> {
        val holder = Registry.registerForHolder(Regs.ITEM, id, supplier.get())
        return DeferredItem { holder }
    }

    override fun <T : Block> registerBlock(
        id: ResourceLocation,
        supplier: Supplier<T>
    ): DeferredBlock<T> {
        val holder = Registry.registerForHolder(Regs.BLOCK, id, supplier.get())
        return DeferredBlock { holder }
    }

    override fun registerAttribute(
        id: ResourceLocation,
        supplier: Supplier<Attribute>
    ): DeferredAttribute {
        val holder = Registry.registerForHolder(Regs.ATTRIBUTE, id, supplier.get())
        return DeferredAttribute { holder }
    }

    override fun <T : Entity> registerEntity(
        id: ResourceLocation,
        supplier: Supplier<EntityType<T>>
    ): DeferredEntity<T> {
        val holder = Registry.registerForHolder(Regs.ENTITY_TYPE, id, supplier.get())
        return DeferredEntity { holder }
    }

    override fun <T : BlockEntity> registerBlockEntity(
        id: ResourceLocation,
        supplier: Supplier<BlockEntityType<T>>
    ): DeferredBlockEntity<T> {
        val holder = Registry.registerForHolder(Regs.BLOCK_ENTITY_TYPE, id, supplier.get())
        return DeferredBlockEntity { holder }
    }

    override fun <T : MobEffect> registerMobEffect(
        id: ResourceLocation,
        supplier: Supplier<T>
    ): DeferredMobEffect<T> {
        val holder = Registry.registerForHolder(Regs.MOB_EFFECT, id, supplier.get())
        return DeferredMobEffect { holder }
    }

    override fun <T : ParticleOptions> registerParticle(
        id: ResourceLocation,
        supplier: Supplier<ParticleType<T>>
    ): DeferredParticle<T> {
        val holder = Registry.registerForHolder(Regs.PARTICLE_TYPE, id, supplier.get())
        return DeferredParticle { holder }
    }

    override fun <T> newRegistry(
        key: ResourceKey<Registry<T>>
    ): UnifiedRegistry<T> {
        return FabricUnifiedRegistry(
            FabricRegistryBuilder
                .createSimple(key)
                .attribute(RegistryAttribute.SYNCED)
                .buildAndRegister()
        )
    }

    override fun <T> newDataRegistry(
        key: ResourceKey<Registry<T>>,
        codec: Supplier<Codec<T>>
    ) {
        DynamicRegistries.register(key, codec.get())
    }

    override fun <T> newSyncedDataRegistry(
        key: ResourceKey<Registry<T>>,
        codec: Supplier<Codec<T>>
    ) {
        DynamicRegistries.registerSynced(key, codec.get())
    }

    override fun <T> newSyncedDataRegistry(
        key: ResourceKey<Registry<T>>,
        codec: Supplier<Codec<T>>,
        networkCodec: Supplier<Codec<T>>
    ) {
        DynamicRegistries.registerSynced(key, codec.get(), networkCodec.get())
    }

    override fun registerAttributes(
        type: DeferredEntity<out LivingEntity>,
        builder: AttributeSupplier.Builder
    ) {
        FabricDefaultAttributeRegistry.register(type.get(), builder)
    }
}