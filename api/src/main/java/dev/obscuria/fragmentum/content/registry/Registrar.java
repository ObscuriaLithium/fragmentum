package dev.obscuria.fragmentum.content.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface Registrar {

    <T, V extends T> Deferred<T, V> register(Registry<T> registry, ResourceLocation id, Supplier<V> supplier);

    <T, V extends T> Deferred<T, V> register(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation id, Supplier<V> supplier);

    <T extends Item> DeferredItem<T> registerItem(ResourceLocation id, Supplier<T> supplier);

    <T extends Block> DeferredBlock<T> registerBlock(ResourceLocation id, Supplier<T> supplier);

    <T extends Entity> DeferredEntity<T> registerEntity(ResourceLocation id, Supplier<EntityType<T>> supplier);

    <T extends BlockEntity> DeferredBlockEntity<T> registerBlockEntity(ResourceLocation id, Supplier<BlockEntityType<T>> supplier);

    <T extends MobEffect> DeferredMobEffect<T> registerMobEffect(ResourceLocation id, Supplier<MobEffect> supplier);

    <T extends ParticleOptions> DeferredParticle<T> registerParticle(ResourceLocation id, Supplier<ParticleType<T>> supplier);

    DeferredAttribute registerAttribute(ResourceLocation id, Supplier<Attribute> supplier);

    <T> DelegatedRegistry<T> createRegistry(ResourceKey<Registry<T>> registryKey);

    <T> void createDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec);

    <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec);

    <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec, Supplier<Codec<T>> networkCodec);

    void registerAttributes(DeferredEntity<? extends LivingEntity> entity, AttributeSupplier.Builder builder);
}
