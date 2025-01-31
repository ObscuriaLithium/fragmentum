package dev.obscuria.fragmentum.forge.service;

import dev.obscuria.fragmentum.api.Deferred;
import dev.obscuria.fragmentum.api.DeferredEntity;
import dev.obscuria.fragmentum.api.DeferredItem;
import dev.obscuria.fragmentum.api.DeferredParticle;
import dev.obscuria.fragmentum.api.v1.client.IClientRegistrar;
import dev.obscuria.fragmentum.forge.ForgeFragmentum;
import net.minecraft.client.KeyMapping;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;

@ApiStatus.Internal
public record ForgeClientRegistrar(String modId) implements IClientRegistrar
{
    @Override
    public <T extends Entity> void registerEntityRenderer(
            DeferredEntity<T> type,
            EntityRendererProvider<T> provider)
    {
        ForgeFragmentum.addListener(modId, (final EntityRenderersEvent.RegisterRenderers event) ->
                event.registerEntityRenderer(type.value(), provider));
    }

    @Override
    public <T extends BlockEntity> void registerBlockEntityRenderer(
            Deferred<BlockEntityType<?>, BlockEntityType<T>> type,
            BlockEntityRendererProvider<T> provider)
    {
        ForgeFragmentum.addListener(modId, (final EntityRenderersEvent.RegisterRenderers event) ->
                event.registerBlockEntityRenderer(type.value(), provider));
    }

    @Override
    public <T extends ParticleOptions> void registerParticleRenderer(
            DeferredParticle<T> type,
            ParticleProvider<T> provider)
    {
        ForgeFragmentum.addListener(modId, (final RegisterParticleProvidersEvent event) ->
                event.registerSpecial(type.value(), provider));
    }

    @Override
    public <T extends ParticleOptions> void registerTexturedParticleRenderer(
            DeferredParticle<T> type,
            TexturedParticleProvider<T> provider)
    {
        ForgeFragmentum.addListener(modId, (final RegisterParticleProvidersEvent event) ->
                event.registerSpriteSet(type.value(), provider::create));
    }

    @Override
    public void registerModelLayer(
            ModelLayerLocation layerLocation,
            ModelLayerProvider provider)
    {
        ForgeFragmentum.addListener(modId, (final EntityRenderersEvent.RegisterLayerDefinitions event) ->
                event.registerLayerDefinition(layerLocation, provider::create));
    }

    @Override
    public void registerItemColor(
            ItemColorProvider provider,
            DeferredItem<?>... items)
    {
        ForgeFragmentum.addListener(modId, (final RegisterColorHandlersEvent.Item event) ->
                event.register(provider::getColor,
                        Arrays.stream(items)
                                .map(Deferred::value)
                                .toArray(Item[]::new)));
    }

    @Override
    public void registerItemProperty(
            ResourceLocation key,
            ClampedItemPropertyFunction function)
    {
        ItemProperties.registerGeneric(key, function);
    }

    @Override
    public void registerKeyMapping(KeyMapping keyMapping)
    {
        ForgeFragmentum.addListener(modId, (final RegisterKeyMappingsEvent event) ->
                event.register(keyMapping));
    }
}
