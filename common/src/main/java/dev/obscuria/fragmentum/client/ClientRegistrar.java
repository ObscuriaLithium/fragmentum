package dev.obscuria.fragmentum.client;

import dev.obscuria.fragmentum.registry.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("unused")
public interface ClientRegistrar
{
    <T extends Entity> void registerEntityRenderer(DeferredEntity<T> entity, EntityRendererProvider<T> provider);

    <T extends BlockEntity> void registerBlockEntityRenderer(DeferredBlockEntity<T> entity, BlockEntityRendererProvider<T> provider);

    <T extends ParticleOptions> void registerParticleRenderer(DeferredParticle<T> particle, ParticleProvider<T> provider);

    <T extends ParticleOptions> void registerTexturedParticleRenderer(DeferredParticle<T> particle, TexturedParticleProvider<T> provider);

    void registerModelLayer(ModelLayerLocation location, ModelLayerProvider provider);

    void registerItemColor(ItemColorProvider provider, DeferredItem<?>... items);

    void registerBlockColor(BlockColorProvider provider, DeferredBlock<?>... blocks);

    void registerItemProperty(ResourceLocation key, ClampedItemPropertyFunction function);

    @FunctionalInterface
    interface TexturedParticleProvider<T extends ParticleOptions>
    {
        ParticleProvider<T> create(SpriteSet spriteSet);
    }

    @FunctionalInterface
    interface ModelLayerProvider
    {
        LayerDefinition create();
    }

    @FunctionalInterface
    interface ItemColorProvider
    {
        int pick(ItemStack stack, int layer);
    }

    @FunctionalInterface
    interface BlockColorProvider
    {
        int pick(BlockState state, @Nullable BlockAndTintGetter getter, @Nullable BlockPos pos, int layer);
    }
}
