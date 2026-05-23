package dev.obscuria.fragmentum.v2.api.common.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
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

    <T, V extends T> Deferred<T> register(Registry<T> registry, Identifier id, Supplier<V> supplier);

    <T, V extends T> Deferred<T> register(ResourceKey<? extends Registry<T>> registryKey, Identifier id, Supplier<V> supplier);

    <T extends Item> DeferredItem<T> registerItem(Identifier id, Supplier<T> supplier);

    <T extends Block> DeferredBlock<T> registerBlock(Identifier id, Supplier<T> supplier);

    <T extends Entity> DeferredEntity<T> registerEntity(Identifier id, Supplier<EntityType<T>> supplier);

    <T extends BlockEntity> DeferredBlockEntity<T> registerBlockEntity(Identifier id, Supplier<BlockEntityType<T>> supplier);

    <T extends ParticleOptions> DeferredParticle<T> registerParticle(Identifier id, Supplier<ParticleType<T>> supplier);

    DeferredAttribute registerAttribute(Identifier id, Supplier<Attribute> supplier);

    <T> Registry<T> createRegistry(ResourceKey<Registry<T>> registryKey);

    <T> void createDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec);

    <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec);

    <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec, Supplier<Codec<T>> networkCodec);

    void registerAttributes(DeferredEntity<? extends LivingEntity> entity, AttributeSupplier.Builder builder);
}
