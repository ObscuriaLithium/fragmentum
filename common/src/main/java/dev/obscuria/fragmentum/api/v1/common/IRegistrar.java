package dev.obscuria.fragmentum.api.v1.common;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.api.*;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

/**
 * An interface for managing the registration of Minecraft components.
 */
@SuppressWarnings("unused")
public interface IRegistrar
{
    /**
     * Registers a generic object in the specified Minecraft registry using a resource key.
     *
     * @param <T>          The type of the object being registered.
     * @param <V>          The specific type that extends or implements the base type.
     * @param registryKey  The {@link ResourceKey} of the registry.
     * @param id           The {@link ResourceLocation} identifier for the object.
     * @param supplier     A {@link Supplier} that provides instances of the object.
     * @return A {@link Deferred} object representing the registered component.
     */
    <T, V extends T> Deferred<T, V> register(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation id, Supplier<V> supplier);

    /**
     * Registers a generic object in the given registry with a specified identifier and supplier.
     *
     * @param <T>       The type of the object being registered.
     * @param <V>       The specific type that extends or implements the base type.
     * @param registry  The {@link Registry} in which the object is registered.
     * @param id        The {@link ResourceLocation} identifier for the object.
     * @param supplier  A {@link Supplier} that provides instances of the object.
     * @return A {@link Deferred} object representing the registered component.
     */
    <T, V extends T> Deferred<T, V> register(Registry<T> registry, ResourceLocation id, Supplier<V> supplier);

    /**
     * Registers an item in the Minecraft with the given identifier and supplier.
     *
     * @param <T>       The type of the {@link Item}.
     * @param id        The {@link ResourceLocation} identifier for the item.
     * @param supplier  A {@link Supplier} that provides instances of the item.
     * @return A {@link DeferredItem} representing the registered item.
     */
    <T extends Item> DeferredItem<T> registerItem(ResourceLocation id, Supplier<T> supplier);

    /**
     * Registers an attribute in Minecraft with the given identifier and supplier.
     *
     * @param id        The {@link ResourceLocation} identifier for the attribute.
     * @param supplier  A {@link Supplier} that provides instances of the {@link Attribute}.
     * @return A {@link DeferredAttribute} representing the registered attribute.
     */
    DeferredAttribute registerAttribute(ResourceLocation id, Supplier<Attribute> supplier);

    /**
     * Registers an entity type in the Minecraft with the given identifier and supplier.
     *
     * @param <T>       The type of the {@link Entity}.
     * @param id        The {@link ResourceLocation} identifier for the entity.
     * @param supplier  A {@link Supplier} that provides instances of the entity type.
     * @return A {@link DeferredEntity} representing the registered entity type.
     */
    <T extends Entity> DeferredEntity<T> registerEntity(ResourceLocation id, Supplier<EntityType<T>> supplier);

    /**
     * Registers a block entity type in Minecraft with the given identifier and supplier.
     *
     * @param <T>       The type of the {@link BlockEntity}.
     * @param id        The {@link ResourceLocation} identifier for the block entity.
     * @param supplier  A {@link Supplier} that provides instances of the block entity type.
     * @return A {@link DeferredBlockEntity} representing the registered block entity type.
     */
    <T extends BlockEntity> DeferredBlockEntity<T> registerBlockEntity(ResourceLocation id, Supplier<BlockEntityType<T>> supplier);

    /**
     * Registers a mob effect in Minecraft with the given identifier and supplier.
     *
     * @param <T>       The type of the {@link MobEffect}.
     * @param id        The {@link ResourceLocation} identifier for the mob effect.
     * @param supplier  A {@link Supplier} that provides instances of the mob effect.
     * @return A {@link DeferredMobEffect} representing the registered mob effect.
     */
    <T extends MobEffect> DeferredMobEffect<T> registerMobEffect(ResourceLocation id, Supplier<T> supplier);

    /**
     * Registers a particle type in the Minecraft with the given identifier and supplier.
     *
     * @param <T>       The type of the {@link ParticleOptions}.
     * @param id        The {@link ResourceLocation} identifier for the particle.
     * @param supplier  A {@link Supplier} that provides instances of the particle type.
     * @return A {@link DeferredParticle} representing the registered particle type.
     */
    <T extends ParticleOptions> DeferredParticle<T> registerParticle(ResourceLocation id, Supplier<ParticleType<T>> supplier);

    /**
     * Creates a new custom registry for the specified {@link ResourceKey}.
     *
     * @param <T> The type of the objects to be stored in the new registry.
     * @param key The {@link ResourceKey} representing the custom registry to be created.
     * @return A {@link UnifiedRegistry} instance for interacting with the custom registry.
     */
    <T> UnifiedRegistry<T> newRegistry(ResourceKey<Registry<T>> key);

    /**
     * Creates a new data registry for storing custom game data.
     *
     * @param <T>   The type of the data stored in the registry.
     * @param key   The {@link ResourceKey} for the registry.
     * @param codec The {@link Codec} used to serialize and deserialize the data.
     */
    <T> void newDataRegistry(ResourceKey<Registry<T>> key, Supplier<Codec<T>> codec);

    /**
     * Creates a new synced data registry for storing and synchronizing custom game data between client and server.
     *
     * @param <T>   The type of the data stored in the registry.
     * @param key   The {@link ResourceKey} for the registry.
     * @param codec The {@link Codec} used to serialize and deserialize the data.
     */
    <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key, Supplier<Codec<T>> codec);

    /**
     * Creates a new synced data registry with separate codecs for local storage and network synchronization.
     *
     * @param <T>           The type of the data stored in the registry.
     * @param key           The {@link ResourceKey} for the registry.
     * @param dataCodec     The {@link Codec} used for local serialization and deserialization.
     * @param networkCodec  The {@link Codec} used for network serialization and deserialization.
     */
    <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key, Supplier<Codec<T>> dataCodec, Supplier<Codec<T>> networkCodec);

    /**
     * Registers custom attributes for the given entity type.
     *
     * @param type    A {@link DeferredEntity} referring to the entity type (e.g., {@link LivingEntity}).
     * @param builder An {@link AttributeSupplier.Builder} used to define the attributes for the entity.
     */
    void registerAttributes(DeferredEntity<? extends LivingEntity> type, AttributeSupplier.Builder builder);
}