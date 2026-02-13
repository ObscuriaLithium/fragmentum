package dev.obscuria.fragmentum.content.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface Registrar {

    <R, T extends R> Deferred<T> register(Registry<R> registry, Identifier id, Supplier<T> supplier);

    <R, T extends R> Deferred<T> register(ResourceKey<? extends Registry<R>> registryKey, Identifier id, Supplier<T> supplier);

    <T extends Entity> DeferredEntity<T> registerEntity(Identifier id, Supplier<EntityType<T>> supplier);

    <T extends BlockEntity> DeferredBlockEntity<T> registerBlockEntity(Identifier id, Supplier<BlockEntityType<T>> supplier);

    <T extends ParticleOptions> DeferredParticle<T> registerParticle(Identifier id, Supplier<ParticleType<T>> supplier);

    <T> Registry<T> createRegistry(ResourceKey<Registry<T>> registryKey);

    <T> void createDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec);

    <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec);

    <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec, Supplier<Codec<T>> networkCodec);

    void registerAttributes(DeferredEntity<? extends LivingEntity> entity, AttributeSupplier.Builder builder);
}
