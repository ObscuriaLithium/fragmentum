package dev.obscuria.fragmentum.fabric.registry;

import dev.obscuria.fragmentum.client.ClientRegistrar;
import dev.obscuria.fragmentum.content.registry.*;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Arrays;

public record FabricClientRegistrar(String modId) implements ClientRegistrar {

    @Override
    public <T extends Entity> void registerEntityRenderer(DeferredEntity<T> entity, EntityRendererProvider<T> provider) {
        EntityRenderers.register(entity.get(), provider);
    }

    @Override
    public <T extends BlockEntity, S extends BlockEntityRenderState> void registerBlockEntityRenderer(DeferredBlockEntity<T> entity, BlockEntityRendererProvider<T, S> provider) {
        BlockEntityRenderers.register(entity.get(), provider);
    }

    @Override
    public <T extends ParticleOptions> void registerParticleRenderer(DeferredParticle<T> particle, ParticleProvider<T> provider) {
        ParticleFactoryRegistry.getInstance().register(particle.get(), provider);
    }

    @Override
    public <T extends ParticleOptions> void registerTexturedParticleRenderer(DeferredParticle<T> particle, TexturedParticleProvider<T> provider) {
        ParticleFactoryRegistry.getInstance().register(particle.get(), provider::create);
    }

    @Override
    public void registerModelLayer(ModelLayerLocation location, ModelLayerProvider provider) {
        EntityModelLayerRegistry.registerModelLayer(location, provider::create);
    }

    @Override
    @SafeVarargs
    public final void registerBlockColor(BlockColorProvider provider, Deferred<? extends Block>... blocks) {
        ColorProviderRegistry.BLOCK.register(provider::pick, Arrays.stream(blocks).map(Deferred::get).toArray(Block[]::new));
    }
}