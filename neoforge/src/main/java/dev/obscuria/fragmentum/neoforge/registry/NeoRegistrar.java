package dev.obscuria.fragmentum.neoforge.registry;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.neoforge.NeoFragmentum;
import dev.obscuria.fragmentum.content.registry.*;
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
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public record NeoRegistrar(String modId) implements Registrar {

    @Override
    public <R, T extends R> Deferred<T> register(Registry<R> registry, Identifier id, Supplier<T> supplier) {
        return register(registry.key(), id, supplier);
    }

    @Override
    public <R, T extends R> Deferred<T> register(ResourceKey<? extends Registry<R>> registryKey, Identifier id, Supplier<T> supplier) {
        return new Deferred<>(registerInternal(registryKey, id, supplier));
    }

    @Override
    public <T extends Entity> DeferredEntity<T> registerEntity(Identifier id, Supplier<EntityType<T>> supplier) {
        return new DeferredEntity<>(registerInternal(Registries.ENTITY_TYPE, id, supplier));
    }

    @Override
    public <T extends BlockEntity> DeferredBlockEntity<T> registerBlockEntity(Identifier id, Supplier<BlockEntityType<T>> supplier) {
        return new DeferredBlockEntity<>(registerInternal(Registries.BLOCK_ENTITY_TYPE, id, supplier));
    }

    @Override
    public <T extends ParticleOptions> DeferredParticle<T> registerParticle(Identifier id, Supplier<ParticleType<T>> supplier) {
        return new DeferredParticle<>(registerInternal(Registries.PARTICLE_TYPE, id, supplier));
    }

    @Override
    public <T> Registry<T> createRegistry(ResourceKey<Registry<T>> registryKey) {
        final var registry = new RegistryBuilder<>(registryKey).create();
        NeoFragmentum.addListener(modId, (final NewRegistryEvent event) -> event.register(registry));
        return registry;
    }

    @Override
    public <T> void createDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec) {
        NeoFragmentum.addListener(modId, (final DataPackRegistryEvent.NewRegistry event) -> event.dataPackRegistry(registryKey, codec.get()));
    }

    @Override
    public <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec) {
        NeoFragmentum.addListener(modId, (final DataPackRegistryEvent.NewRegistry event) -> event.dataPackRegistry(registryKey, codec.get(), codec.get()));
    }

    @Override
    public <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec, Supplier<Codec<T>> networkCodec) {
        NeoFragmentum.addListener(modId, (final DataPackRegistryEvent.NewRegistry event) -> event.dataPackRegistry(registryKey, codec.get(), networkCodec.get()));
    }

    @Override
    public void registerAttributes(DeferredEntity<? extends LivingEntity> entity, AttributeSupplier.Builder builder) {
        NeoFragmentum.addListener(modId, (final EntityAttributeCreationEvent event) -> event.put(entity.get(), builder.build()));
    }

    @SuppressWarnings("unchecked")
    private <R, T extends R> Holder<T> registerInternal(ResourceKey<? extends Registry<R>> key, Identifier id, Supplier<? extends T> supplier) {
        final var deferredRegister = DeferredRegister.create(key, modId);
        NeoFragmentum.register(modId, deferredRegister);
        return (Holder<T>) deferredRegister.register(id.getPath(), supplier);
    }
}
