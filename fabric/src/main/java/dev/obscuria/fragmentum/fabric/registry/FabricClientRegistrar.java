package dev.obscuria.fragmentum.fabric.registry;

import dev.obscuria.fragmentum.v2.api.client.ClientRegistrar;
import dev.obscuria.fragmentum.v2.api.common.registry.DeferredBlock;
import dev.obscuria.fragmentum.v2.api.common.registry.DeferredBlockEntity;
import dev.obscuria.fragmentum.v2.api.common.registry.DeferredEntity;
import dev.obscuria.fragmentum.v2.api.common.registry.DeferredParticle;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.color.block.BlockTintSource;
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
import java.util.List;

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
        ParticleProviderRegistry.getInstance().register(particle.get(), provider);
    }

    @Override
    public <T extends ParticleOptions> void registerTexturedParticleRenderer(DeferredParticle<T> particle, TexturedParticleProvider<T> provider) {
        ParticleProviderRegistry.getInstance().register(particle.get(), provider::create);
    }

    @Override public void registerModelLayer(ModelLayerLocation location, ModelLayerProvider provider) {
        ModelLayerRegistry.registerModelLayer(location, provider::create);
    }

    @Override public void registerBlockColor(List<BlockTintSource> layers, DeferredBlock<?>... blocks) {
        BlockColorRegistry.register(layers, Arrays.stream(blocks).map(DeferredBlock::get).toArray(Block[]::new));
    }
}