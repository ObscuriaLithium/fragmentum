package dev.obscuria.fragmentum.api.v1.client;

import dev.obscuria.fragmentum.api.Deferred;
import dev.obscuria.fragmentum.api.DeferredEntity;
import dev.obscuria.fragmentum.api.DeferredItem;
import dev.obscuria.fragmentum.api.DeferredParticle;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * A client-side registry interface for registering Minecraft rendering and client-specific behavior,
 * such as entity renderers, particle providers, model layers, item colors, key mappings, and more.
 */
@SuppressWarnings("unused")
public interface IClientRegistrar
{
    /**
     * Registers a renderer for a specific {@link EntityType}.
     *
     * @param <T>      The type of the {@link Entity}.
     * @param type     A {@link DeferredEntity} reference to the {@link EntityType} to associate with the renderer.
     * @param provider The {@link EntityRendererProvider} responsible for creating the renderer for the entity.
     */
    <T extends Entity> void registerEntityRenderer(
            DeferredEntity<T> type,
            EntityRendererProvider<T> provider);

    /**
     * Registers a renderer for a specific {@link BlockEntityType}.
     *
     * @param <T>      The type of the {@link BlockEntity}.
     * @param type     A {@link Deferred} reference to the {@link BlockEntityType} to associate with the renderer.
     * @param provider The {@link BlockEntityRendererProvider} responsible for providing the renderer.
     */
    <T extends BlockEntity> void registerBlockEntityRenderer(
            Deferred<BlockEntityType<?>, BlockEntityType<T>> type,
            BlockEntityRendererProvider<T> provider);

    /**
     * Registers a renderer for a specific {@link ParticleType}.
     *
     * @param <T>      The type of the {@link ParticleOptions}.
     * @param type     A {@link DeferredParticle} reference to the {@link ParticleType}.
     * @param provider The {@link ParticleProvider} for the particle.
     */
    <T extends ParticleOptions> void registerParticleRenderer(
            DeferredParticle<T> type,
            ParticleProvider<T> provider);

    /**
     * Registers a particle renderer with a {@link SpriteSet} for textured particles.
     *
     * @param <T>      The type of the {@link ParticleOptions}.
     * @param type     A {@link DeferredParticle} reference to the {@link ParticleType}.
     * @param provider A {@link TexturedParticleProvider} that creates a {@link ParticleProvider} for visualizing particles with textures.
     */
    <T extends ParticleOptions> void registerTexturedParticleRenderer(
            DeferredParticle<T> type,
            TexturedParticleProvider<T> provider);

    /**
     * Registers a model layer definition used for rendering entity models.
     *
     * @param layerLocation The {@link ModelLayerLocation} associated with the model layer.
     * @param provider      A {@link ModelLayerProvider} responsible for creating the {@link LayerDefinition}.
     */
    void registerModelLayer(
            ModelLayerLocation layerLocation,
            ModelLayerProvider provider);

    /**
     * Registers a color provider for specific items.
     *
     * @param provider The {@link ItemColorProvider} responsible for determining item colors.
     * @param items    A vararg array of {@link DeferredItem} references.
     */
    void registerItemColor(
            ItemColorProvider provider,
            DeferredItem<?>... items);

    /**
     * Registers a property override for specific items.
     *
     * @param key      A {@link ResourceLocation} key that identifies the property.
     * @param function A {@link ClampedItemPropertyFunction} that defines the behavior of the property.
     */
    void registerItemProperty(
            ResourceLocation key,
            ClampedItemPropertyFunction function);

    /**
     * Registers a key mapping to enable client-specific key bindings.
     *
     * @param keyMapping The {@link KeyMapping} to register.
     */
    void registerKeyMapping(KeyMapping keyMapping);

    /**
     * Functional interface for providing model layer definitions.
     */
    @FunctionalInterface
    interface ModelLayerProvider
    {
        /**
         * Creates a {@link LayerDefinition} for constructing model layers.
         *
         * @return A {@link LayerDefinition} instance.
         */
        LayerDefinition create();
    }

    /**
     * Functional interface for creating textured particle providers.
     *
     * @param <T> The type of the {@link ParticleOptions}.
     */
    @FunctionalInterface
    interface TexturedParticleProvider<T extends ParticleOptions>
    {
        /**
         * Creates a {@link ParticleProvider} using the given {@link SpriteSet}.
         *
         * @param spriteSet The {@link SpriteSet} providing textures for the particle.
         * @return A {@link ParticleProvider} for rendering the particle.
         */
        ParticleProvider<T> create(SpriteSet spriteSet);
    }

    /**
     * Functional interface for providing custom item colors.
     */
    @FunctionalInterface
    interface ItemColorProvider
    {
        /**
         * Gets the color of the specified {@link ItemStack} based on its layer.
         *
         * @param stack The {@link ItemStack} being rendered.
         * @param layer The rendering layer.
         * @return An integer representing the color of the item for the specified layer.
         */
        int getColor(ItemStack stack, int layer);
    }
}