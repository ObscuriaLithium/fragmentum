package dev.obscuria.fragmentum.api.v1.client;

import dev.obscuria.fragmentum.api.Deferred;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.ApiStatus;

import java.util.ServiceLoader;

@ApiStatus.Internal
public interface V1Client
{
    V1Client INSTANCE = ServiceLoader.load(V1Client.class).findFirst().orElseThrow();

    <E extends Entity> void
    registerEntityRenderer(String modId,
                           Deferred<EntityType<?>, EntityType<E>> type,
                           EntityRendererProvider<E> provider);

    <T extends BlockEntity> void
    registerBlockEntityRenderer(String modId,
                                Deferred<BlockEntityType<?>, BlockEntityType<T>> type,
                                BlockEntityRendererProvider<T> provider);

    <T extends ParticleOptions> void
    registerParticleRenderer(String modId,
                             Deferred<ParticleType<?>, ParticleType<T>> type,
                             ParticleProvider<T> provider);

    <T extends ParticleOptions> void
    registerTexturedParticleRenderer(String modId,
                                     Deferred<ParticleType<?>, ParticleType<T>> type,
                                     ObscureClientRegistry.TexturedParticleProvider<T> provider);

    void registerModelLayer(String modId,
                            ModelLayerLocation layerLocation,
                            ObscureClientRegistry.ModelLayerProvider provider);

    @SuppressWarnings("unchecked")
    void registerItemColor(String modId,
                           ObscureClientRegistry.ItemColorProvider provider,
                           Deferred<Item, ? extends Item>... items);

    void registerItemProperty(String modId,
                              ResourceLocation key,
                              ClampedItemPropertyFunction function);

    void registerKeyMapping(String modId,
                            KeyMapping keyMapping);
}
