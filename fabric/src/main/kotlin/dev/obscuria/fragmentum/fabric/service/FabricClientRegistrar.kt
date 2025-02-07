package dev.obscuria.fragmentum.fabric.service

import dev.obscuria.fragmentum.api.client.ClientRegistrar
import dev.obscuria.fragmentum.api.client.ClientRegistrar.*
import dev.obscuria.fragmentum.api.registry.DeferredBlockEntity
import dev.obscuria.fragmentum.api.registry.DeferredEntity
import dev.obscuria.fragmentum.api.registry.DeferredItem
import dev.obscuria.fragmentum.api.registry.DeferredParticle
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.KeyMapping
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.block.entity.BlockEntity

@JvmRecord
internal data class FabricClientRegistrar(val modId: String) : ClientRegistrar
{
    override fun <T : Entity> registerEntityRenderer(
        type: DeferredEntity<T>,
        provider: EntityRendererProvider<T>
    )
    {
        EntityRendererRegistry.register(type.value(), provider)
    }

    override fun <T : BlockEntity> registerBlockEntityRenderer(
        type: DeferredBlockEntity<T>,
        provider: BlockEntityRendererProvider<T>
    )
    {
        BlockEntityRenderers.register(type.value(), provider)
    }

    override fun <T : ParticleOptions> registerParticleRenderer(
        type: DeferredParticle<T>,
        provider: ParticleProvider<T>
    )
    {
        ParticleFactoryRegistry.getInstance().register(type.value(), provider)
    }

    override fun <T : ParticleOptions> registerTexturedParticleRenderer(
        type: DeferredParticle<T>,
        provider: TexturedParticleProvider<T>
    )
    {
        ParticleFactoryRegistry.getInstance().register(type.value(), provider::create)
    }

    override fun registerModelLayer(
        layerLocation: ModelLayerLocation,
        provider: ModelLayerProvider
    )
    {
        EntityModelLayerRegistry.registerModelLayer(layerLocation, provider::create)
    }

    override fun registerItemColor(
        provider: ItemColorProvider,
        vararg items: DeferredItem<*>
    )
    {
        ColorProviderRegistry.ITEM.register(provider::getColor, *items)
    }

    override fun registerItemProperty(
        key: ResourceLocation,
        function: ClampedItemPropertyFunction
    )
    {
        ItemProperties.registerGeneric(key, function)
    }

    override fun registerKeyMapping(keyMapping: KeyMapping)
    {
        KeyBindingHelper.registerKeyBinding(keyMapping)
    }
}