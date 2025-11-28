package dev.obscuria.fragmentum.neoforge.registry;

import dev.obscuria.fragmentum.client.ClientRegistrar;
import dev.obscuria.fragmentum.content.registry.*;
import dev.obscuria.fragmentum.neoforge.NeoFragmentum;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import java.util.Arrays;

public record NeoClientRegistrar(String modId) implements ClientRegistrar {

    @Override
    public <T extends Entity> void registerEntityRenderer(DeferredEntity<T> entity, EntityRendererProvider<T> provider) {
        NeoFragmentum.addListener(modId, (final EntityRenderersEvent.RegisterRenderers event) -> event.registerEntityRenderer(entity.get(), provider));
    }

    @Override
    public <T extends BlockEntity> void registerBlockEntityRenderer(DeferredBlockEntity<T> entity, BlockEntityRendererProvider<T> provider) {
        NeoFragmentum.addListener(modId, (final EntityRenderersEvent.RegisterRenderers event) -> event.registerBlockEntityRenderer(entity.get(), provider));
    }

    @Override
    public <T extends ParticleOptions> void registerParticleRenderer(DeferredParticle<T> particle, ParticleProvider<T> provider) {
        NeoFragmentum.addListener(modId, (final RegisterParticleProvidersEvent event) -> event.registerSpecial(particle.get(), provider));
    }

    @Override
    public <T extends ParticleOptions> void registerTexturedParticleRenderer(DeferredParticle<T> particle, TexturedParticleProvider<T> provider) {
        NeoFragmentum.addListener(modId, (final RegisterParticleProvidersEvent event) -> event.registerSpriteSet(particle.get(), provider::create));
    }

    @Override
    public void registerModelLayer(ModelLayerLocation location, ModelLayerProvider provider) {
        NeoFragmentum.addListener(modId, (final EntityRenderersEvent.RegisterLayerDefinitions event) -> event.registerLayerDefinition(location, provider::create));
    }

    @Override
    public void registerItemColor(ItemColorProvider provider, DeferredItem<?>... items) {
        NeoFragmentum.addListener(modId, (final RegisterColorHandlersEvent.Item event) -> event.register(provider::pick, Arrays.stream(items).map(Deferred::get).toArray(Item[]::new)));
    }

    @Override
    public void registerBlockColor(BlockColorProvider provider, DeferredBlock<?>... blocks) {
        NeoFragmentum.addListener(modId, (final RegisterColorHandlersEvent.Block event) -> event.register(provider::pick, Arrays.stream(blocks).map(Deferred::get).toArray(Block[]::new)));
    }

    @Override
    public void registerItemProperty(ResourceLocation key, ClampedItemPropertyFunction function) {
        ItemProperties.registerGeneric(key, function);
    }
}
