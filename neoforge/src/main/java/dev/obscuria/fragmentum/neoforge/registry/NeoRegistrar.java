package dev.obscuria.fragmentum.neoforge.registry;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.neoforge.NeoFragmentum;
import dev.obscuria.fragmentum.content.registry.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
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
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public record NeoRegistrar(String modId) implements Registrar {

    @Override
    public <T, V extends T> Deferred<T, V> register(Registry<T> registry, ResourceLocation id, Supplier<V> supplier) {
        return register(registry.key(), id, supplier);
    }

    @Override
    public <T, V extends T> Deferred<T, V> register(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation id, Supplier<V> supplier) {
        return new Deferred<>(this.registerInternal(registryKey, id, supplier));
    }

    @Override
    public <T extends Item> DeferredItem<T> registerItem(ResourceLocation id, Supplier<T> supplier) {
        return new DeferredItem<>(this.registerInternal(Registries.ITEM, id, supplier));
    }

    @Override
    public <T extends Block> DeferredBlock<T> registerBlock(ResourceLocation id, Supplier<T> supplier) {
        return new DeferredBlock<>(this.registerInternal(Registries.BLOCK, id, supplier));
    }

    @Override
    public <T extends Entity> DeferredEntity<T> registerEntity(ResourceLocation id, Supplier<EntityType<T>> supplier) {
        return new DeferredEntity<>(this.registerInternal(Registries.ENTITY_TYPE, id, supplier));
    }

    @Override
    public <T extends BlockEntity> DeferredBlockEntity<T> registerBlockEntity(ResourceLocation id, Supplier<BlockEntityType<T>> supplier) {
        return new DeferredBlockEntity<>(this.registerInternal(Registries.BLOCK_ENTITY_TYPE, id, supplier));
    }

    @Override
    public <T extends MobEffect> DeferredMobEffect<T> registerMobEffect(ResourceLocation id, Supplier<MobEffect> supplier) {
        return new DeferredMobEffect<>(this.registerInternal(Registries.MOB_EFFECT, id, supplier));
    }

    @Override
    public <T extends ParticleOptions> DeferredParticle<T> registerParticle(ResourceLocation id, Supplier<ParticleType<T>> supplier) {
        return new DeferredParticle<>(this.registerInternal(Registries.PARTICLE_TYPE, id, supplier));
    }

    @Override
    public DeferredAttribute registerAttribute(ResourceLocation id, Supplier<Attribute> supplier) {
        return new DeferredAttribute(this.registerInternal(Registries.ATTRIBUTE, id, supplier));
    }

    @Override
    public <T> DelegatedRegistry<T> createRegistry(ResourceKey<Registry<T>> registryKey) {
        final var registry = new RegistryBuilder<>(registryKey).create();
        NeoFragmentum.addListener(modId, (final NewRegistryEvent event) -> event.register(registry));
        return new NeoDelegatedRegistry<>(registry);
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

    private <T> Lazy<Holder<T>> registerInternal(ResourceKey<? extends Registry<T>> key, ResourceLocation id, Supplier<? extends T> supplier) {
        final var deferredRegister = DeferredRegister.create(key, modId);
        NeoFragmentum.register(modId, deferredRegister);
        final var object = deferredRegister.register(id.getPath(), supplier);
        return Lazy.of(() -> object);
    }
}
