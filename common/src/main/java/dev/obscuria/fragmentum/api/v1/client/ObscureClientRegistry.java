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
public interface ObscureClientRegistry
{
    static <E extends Entity> void
    registerEntityRenderer(String modId,
                           Deferred<EntityType<?>, EntityType<E>> type,
                           EntityRendererProvider<E> provider)
    {
        V1Client.INSTANCE.registerEntityRenderer(modId, type, provider);
    }

    static <T extends BlockEntity> void
    registerBlockEntityRenderer(String modId,
                                Deferred<BlockEntityType<?>, BlockEntityType<T>> type,
                                BlockEntityRendererProvider<T> provider)
    {
        V1Client.INSTANCE.registerBlockEntityRenderer(modId, type, provider);
    }

    static <T extends ParticleOptions> void
    registerParticleRenderer(String modId,
                             Deferred<ParticleType<?>, ParticleType<T>> type,
                             ParticleProvider<T> provider)
    {
        V1Client.INSTANCE.registerParticleRenderer(modId, type, provider);
    }

    static <T extends ParticleOptions> void
    registerTexturedParticleRenderer(String modId,
                                     Deferred<ParticleType<?>, ParticleType<T>> type,
                                     TexturedParticleProvider<T> provider)
    {
        V1Client.INSTANCE.registerTexturedParticleRenderer(modId, type, provider);
    }

    static void registerModelLayer(String modId,
                                   ModelLayerLocation layerLocation,
                                   ModelLayerProvider provider)
    {
        V1Client.INSTANCE.registerModelLayer(modId, layerLocation, provider);
    }

    @SafeVarargs
    static void registerItemColor(String modId,
                                  ItemColorProvider provider,
                                  Deferred<Item, ? extends Item>... items)
    {
        V1Client.INSTANCE.registerItemColor(modId, provider, items);
    }

    static void registerItemProperty(String modId,
                                     ResourceLocation key,
                                     ClampedItemPropertyFunction function)
    {
        V1Client.INSTANCE.registerItemProperty(modId, key, function);
    }

    static void registerKeyMapping(String modId,
                                   KeyMapping keyMapping)
    {
        V1Client.INSTANCE.registerKeyMapping(modId, keyMapping);
    }

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
