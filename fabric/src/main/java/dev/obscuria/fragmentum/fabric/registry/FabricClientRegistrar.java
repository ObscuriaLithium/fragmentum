package dev.obscuria.fragmentum.fabric.registry;

import dev.obscuria.fragmentum.client.ClientRegistrar;
import dev.obscuria.fragmentum.content.registry.*;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Arrays;

public record FabricClientRegistrar(String modId) implements ClientRegistrar {

    @Override
    public <T extends Entity> void registerEntityRenderer(DeferredEntity<T> entity, EntityRendererProvider<T> provider) {
        EntityRendererRegistry.register(entity.get(), provider);
    }

    @Override
    public <T extends BlockEntity> void registerBlockEntityRenderer(DeferredBlockEntity<T> entity, BlockEntityRendererProvider<T> provider) {
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
    public void registerItemColor(ItemColorProvider provider, DeferredItem<?>... items) {
        ColorProviderRegistry.ITEM.register(provider::pick, items);
    }

    @Override
    public void registerBlockColor(BlockColorProvider provider, DeferredBlock<?>... blocks) {
        ColorProviderRegistry.BLOCK.register(provider::pick, Arrays.stream(blocks).map(DeferredBlock::get).toArray(Block[]::new));
    }

    @Override
    public void registerItemProperty(ResourceLocation key, ClampedItemPropertyFunction function) {
        ItemProperties.registerGeneric(key, function);
    }
}
