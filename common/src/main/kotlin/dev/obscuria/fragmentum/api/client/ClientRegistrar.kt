package dev.obscuria.fragmentum.api.client

import dev.obscuria.fragmentum.api.registry.DeferredBlockEntity
import dev.obscuria.fragmentum.api.registry.DeferredEntity
import dev.obscuria.fragmentum.api.registry.DeferredItem
import dev.obscuria.fragmentum.api.registry.DeferredParticle
import net.minecraft.client.KeyMapping
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BlockEntity

interface ClientRegistrar
{
    fun <T : Entity> registerEntityRenderer(
        type: DeferredEntity<T>,
        provider: EntityRendererProvider<T>
    )

    fun <T : BlockEntity> registerBlockEntityRenderer(
        type: DeferredBlockEntity<T>,
        provider: BlockEntityRendererProvider<T>
    )

    fun <T : ParticleOptions> registerParticleRenderer(
        type: DeferredParticle<T>,
        provider: ParticleProvider<T>
    )

    fun <T : ParticleOptions> registerTexturedParticleRenderer(
        type: DeferredParticle<T>,
        provider: TexturedParticleProvider<T>
    )

    fun registerModelLayer(
        layerLocation: ModelLayerLocation,
        provider: ModelLayerProvider
    )

    fun registerItemColor(
        provider: ItemColorProvider,
        vararg items: DeferredItem<*>
    )

    fun registerItemProperty(
        key: ResourceLocation,
        function: ClampedItemPropertyFunction
    )

    fun registerKeyMapping(keyMapping: KeyMapping)

    fun interface ModelLayerProvider
    {
        fun create(): LayerDefinition
    }

    fun interface TexturedParticleProvider<T : ParticleOptions>
    {
        fun create(spriteSet: SpriteSet): ParticleProvider<T>
    }

    fun interface ItemColorProvider
    {
        fun getColor(stack: ItemStack, layer: Int): Int
    }
}