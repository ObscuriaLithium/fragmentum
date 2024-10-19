package dev.obscuria.fragmentum.api.v1.client;

import dev.obscuria.fragmentum.api.Deferred;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

@SuppressWarnings("unused")
public interface IClientRegistrar
{
    <E extends Entity> void
    registerEntityRenderer(Deferred<EntityType<?>, EntityType<E>> type,
                           EntityRendererProvider<E> provider);

    <T extends BlockEntity> void
    registerBlockEntityRenderer(Deferred<BlockEntityType<?>, BlockEntityType<T>> type,
                                BlockEntityRendererProvider<T> provider);

    <T extends ParticleOptions> void
    registerParticleRenderer(Deferred<ParticleType<?>, ParticleType<T>> type,
                             ParticleProvider<T> provider);

    <T extends ParticleOptions> void
    registerTexturedParticleRenderer(Deferred<ParticleType<?>, ParticleType<T>> type,
                                     TexturedParticleProvider<T> provider);

    void registerModelLayer(ModelLayerLocation layerLocation,
                            ModelLayerProvider provider);

    void registerItemColor(ItemColorProvider provider,
                           Deferred<Item, ? extends Item>... items);

    void registerItemProperty(ResourceLocation key,
                              ClampedItemPropertyFunction function);

    void registerKeyMapping(KeyMapping keyMapping);

    @FunctionalInterface
    interface ModelLayerProvider
    {
        LayerDefinition create();
    }

    @FunctionalInterface
    interface TexturedParticleProvider<T extends ParticleOptions>
    {
        ParticleProvider<T> create(SpriteSet spriteSet);
    }

    @FunctionalInterface
    interface ItemColorProvider
    {
        int getColor(ItemStack stack, int layer);
    }
}
