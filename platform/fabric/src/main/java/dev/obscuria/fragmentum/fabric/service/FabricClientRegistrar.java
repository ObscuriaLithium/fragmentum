package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.api.Deferred;
import dev.obscuria.fragmentum.api.v1.client.IClientRegistrar;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Arrays;

public record FabricClientRegistrar(String modId) implements IClientRegistrar
{
    @Override
    public <E extends Entity> void
    registerEntityRenderer(Deferred<EntityType<?>, EntityType<E>> type,
                           EntityRendererProvider<E> provider)
    {
        EntityRendererRegistry.register(type.value(), provider);
    }

    @Override
    public <T extends BlockEntity> void
    registerBlockEntityRenderer(Deferred<BlockEntityType<?>, BlockEntityType<T>> type,
                                BlockEntityRendererProvider<T> provider)
    {
        BlockEntityRenderers.register(type.value(), provider);
    }

    @Override
    public <T extends ParticleOptions> void
    registerParticleRenderer(Deferred<ParticleType<?>, ParticleType<T>> type,
                             ParticleProvider<T> provider)
    {
        ParticleFactoryRegistry.getInstance().register(type.value(), provider);
    }

    @Override
    public <T extends ParticleOptions> void
    registerTexturedParticleRenderer(Deferred<ParticleType<?>, ParticleType<T>> type,
                                     TexturedParticleProvider<T> provider)
    {
        ParticleFactoryRegistry.getInstance().register(type.value(), provider::create);
    }

    @Override
    public void registerModelLayer(ModelLayerLocation layerLocation,
                                   ModelLayerProvider provider)
    {
        EntityModelLayerRegistry.registerModelLayer(layerLocation, provider::create);
    }

    @Override
    @SafeVarargs
    public final void registerItemColor(ItemColorProvider provider,
                                        Deferred<Item, ? extends Item>... items)
    {
        ColorProviderRegistry.ITEM.register(provider::getColor,
                Arrays.stream(items).map(Deferred::value).toArray(Item[]::new));
    }

    @Override
    public void registerItemProperty(ResourceLocation key,
                                     ClampedItemPropertyFunction function)
    {
        ItemProperties.registerGeneric(key, function);
    }

    @Override
    public void registerKeyMapping(KeyMapping keyMapping)
    {
        KeyBindingHelper.registerKeyBinding(keyMapping);
    }
}
