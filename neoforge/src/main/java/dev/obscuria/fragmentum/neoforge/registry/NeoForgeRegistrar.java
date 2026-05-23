package dev.obscuria.fragmentum.neoforge.registry;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.neoforge.NeoForgeFragmentum;
import dev.obscuria.fragmentum.v2.api.common.registry.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public record NeoForgeRegistrar(String modId) implements Registrar {

    @Override public <T, V extends T> Deferred<T> register(Registry<T> registry, Identifier id, Supplier<V> supplier) {
        return register(registry.key(), id, supplier);
    }

    @Override public <T, V extends T> Deferred<T> register(ResourceKey<? extends Registry<T>> registryKey, Identifier id, Supplier<V> supplier) {
        return Deferred.create(registerInternal(registryKey, id, supplier));
    }

    @Override public <T extends Item> DeferredItem<T> registerItem(Identifier id, Supplier<T> supplier) {
        return DeferredItem.create(registerInternal(Registries.ITEM, id, supplier));
    }

    @Override public <T extends Block> DeferredBlock<T> registerBlock(Identifier id, Supplier<T> supplier) {
        return DeferredBlock.create(registerInternal(Registries.BLOCK, id, supplier));
    }

    @Override public <T extends Entity> DeferredEntity<T> registerEntity(Identifier id, Supplier<EntityType<T>> supplier) {
        return DeferredEntity.create(registerInternal(Registries.ENTITY_TYPE, id, supplier));
    }

    @Override public <T extends BlockEntity> DeferredBlockEntity<T> registerBlockEntity(Identifier id, Supplier<BlockEntityType<T>> supplier) {
        return DeferredBlockEntity.create(registerInternal(Registries.BLOCK_ENTITY_TYPE, id, supplier));
    }

    @Override public <T extends ParticleOptions> DeferredParticle<T> registerParticle(Identifier id, Supplier<ParticleType<T>> supplier) {
        return DeferredParticle.create(registerInternal(Registries.PARTICLE_TYPE, id, supplier));
    }

    @Override public DeferredAttribute registerAttribute(Identifier id, Supplier<Attribute> supplier) {
        return DeferredAttribute.create(registerInternal(Registries.ATTRIBUTE, id, supplier));
    }

    @Override public <T> Registry<T> createRegistry(ResourceKey<Registry<T>> registryKey) {
        final var registry = new RegistryBuilder<>(registryKey).create();
        NeoForgeFragmentum.addListener(modId, (final NewRegistryEvent event) -> event.register(registry));
        return registry;
    }

    @Override public <T> void createDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec) {
        NeoForgeFragmentum.addListener(modId, (final DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(registryKey, codec.get()));
    }

    @Override public <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec) {
        NeoForgeFragmentum.addListener(modId, (final DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(registryKey, codec.get(), codec.get()));
    }

    @Override public <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec, Supplier<Codec<T>> networkCodec) {
        NeoForgeFragmentum.addListener(modId, (final DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(registryKey, codec.get(), networkCodec.get()));
    }

    @Override public void registerAttributes(DeferredEntity<? extends LivingEntity> entity, AttributeSupplier.Builder builder) {
        NeoForgeFragmentum.addListener(modId, (final EntityAttributeCreationEvent event) ->
                event.put(entity.get(), builder.build()));
    }

    @SuppressWarnings("unchecked")
    private <R, T extends R> Holder<T> registerInternal(ResourceKey<? extends Registry<R>> key, Identifier id, Supplier<? extends T> supplier) {
        final var deferredRegister = DeferredRegister.create(key, modId);
        NeoForgeFragmentum.register(modId, deferredRegister);
        return (Holder<T>) deferredRegister.register(id.getPath(), supplier);
    }
}
