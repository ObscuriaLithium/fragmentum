package dev.obscuria.fragmentum.neoforge.registry;

import dev.obscuria.fragmentum.neoforge.NeoForgeFragmentum;
import dev.obscuria.fragmentum.v2.api.client.ClientRegistrar;
import dev.obscuria.fragmentum.v2.api.common.registry.*;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import java.util.Arrays;
import java.util.List;

public record NeoForgeClientRegistrar(String modId) implements ClientRegistrar {

    @Override public <T extends Entity> void registerEntityRenderer(DeferredEntity<T> entity, EntityRendererProvider<T> provider) {
        NeoForgeFragmentum.addListener(modId, (final EntityRenderersEvent.RegisterRenderers event) ->
                event.registerEntityRenderer(entity.get(), provider));
    }

    @Override public <T extends BlockEntity, S extends BlockEntityRenderState> void registerBlockEntityRenderer(DeferredBlockEntity<T> entity, BlockEntityRendererProvider<T, S> provider) {
        NeoForgeFragmentum.addListener(modId, (final EntityRenderersEvent.RegisterRenderers event) ->
                event.registerBlockEntityRenderer(entity.get(), provider));
    }

    @Override public <T extends ParticleOptions> void registerParticleRenderer(DeferredParticle<T> particle, ParticleProvider<T> provider) {
        NeoForgeFragmentum.addListener(modId, (final RegisterParticleProvidersEvent event) ->
                event.registerSpecial(particle.get(), provider));
    }

    @Override public <T extends ParticleOptions> void registerTexturedParticleRenderer(DeferredParticle<T> particle, TexturedParticleProvider<T> provider) {
        NeoForgeFragmentum.addListener(modId, (final RegisterParticleProvidersEvent event) ->
                event.registerSpriteSet(particle.get(), provider::create));
    }

    @Override public void registerModelLayer(ModelLayerLocation location, ModelLayerProvider provider) {
        NeoForgeFragmentum.addListener(modId, (final EntityRenderersEvent.RegisterLayerDefinitions event) ->
                event.registerLayerDefinition(location, provider::create));
    }

    @Override public void registerBlockColor(List<BlockTintSource> layers, DeferredBlock<?>... blocks) {
        NeoForgeFragmentum.addListener(modId, (RegisterColorHandlersEvent.BlockTintSources event) ->
                event.register(layers, Arrays.stream(blocks).map(Deferred::get).toArray(Block[]::new)));
    }
}
