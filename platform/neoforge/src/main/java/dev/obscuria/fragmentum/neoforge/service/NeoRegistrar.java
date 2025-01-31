package dev.obscuria.fragmentum.neoforge.service;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.api.*;
import dev.obscuria.fragmentum.api.v1.common.IRegistrar;
import dev.obscuria.fragmentum.api.v1.common.UnifiedRegistry;
import dev.obscuria.fragmentum.neoforge.NeoFragmentum;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.apache.logging.log4j.util.Lazy;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

@ApiStatus.Internal
public record NeoRegistrar(String modId) implements IRegistrar
{
    @Override
    public <T, V extends T> Deferred<T, V> register(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation id, Supplier<V> supplier)
    {
        return new Deferred<>(this.registerInternal(registryKey, id, supplier));
    }

    @Override
    public <T, V extends T> Deferred<T, V> register(Registry<T> registry, ResourceLocation id, Supplier<V> supplier)
    {
        return register(registry.key(), id, supplier);
    }

    @Override
    public <T extends Item> DeferredItem<T> registerItem(ResourceLocation id, Supplier<T> supplier)
    {
        return new DeferredItem<>(this.registerInternal(Registries.ITEM, id, supplier));
    }

    @Override
    public DeferredAttribute registerAttribute(ResourceLocation id, Supplier<Attribute> supplier)
    {
        return new DeferredAttribute(this.registerInternal(Registries.ATTRIBUTE, id, supplier));
    }

    @Override
    public <T extends Entity> DeferredEntity<T> registerEntity(ResourceLocation id, Supplier<EntityType<T>> supplier)
    {
        return new DeferredEntity<>(this.registerInternal(Registries.ENTITY_TYPE, id, supplier));
    }

    @Override
    public <T extends BlockEntity> DeferredBlockEntity<T> registerBlockEntity(ResourceLocation id, Supplier<BlockEntityType<T>> supplier)
    {
        return new DeferredBlockEntity<>(this.registerInternal(Registries.BLOCK_ENTITY_TYPE, id, supplier));
    }

    @Override
    public <T extends MobEffect> DeferredMobEffect<T> registerMobEffect(ResourceLocation id, Supplier<T> supplier)
    {
        return new DeferredMobEffect<>(this.registerInternal(Registries.MOB_EFFECT, id, supplier));
    }

    @Override
    public <T extends ParticleOptions> DeferredParticle<T> registerParticle(ResourceLocation id, Supplier<ParticleType<T>> supplier)
    {
        return new DeferredParticle<>(this.registerInternal(Registries.PARTICLE_TYPE, id, supplier));
    }

    @Override
    public <T> UnifiedRegistry<T> newRegistry(ResourceKey<Registry<T>> key)
    {
        final var registry = new RegistryBuilder<>(key).sync(true).create();
        NeoFragmentum.eventBus(modId).addListener((final NewRegistryEvent event) -> event.register(registry));
        return new NeoUnifiedRegistry<>(registry);
    }

    @Override
    public <T> void newDataRegistry(ResourceKey<Registry<T>> key, Supplier<Codec<T>> codec)
    {
        NeoFragmentum.eventBus(modId).addListener((DataPackRegistryEvent.NewRegistry event) -> event.dataPackRegistry(key, codec.get()));
    }

    @Override
    public <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key, Supplier<Codec<T>> codec)
    {
        NeoFragmentum.eventBus(modId).addListener((DataPackRegistryEvent.NewRegistry event) -> event.dataPackRegistry(key, codec.get(), codec.get()));
    }

    @Override
    public <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key, Supplier<Codec<T>> dataCodec, Supplier<Codec<T>> networkCodec)
    {
        NeoFragmentum.eventBus(modId).addListener((DataPackRegistryEvent.NewRegistry event) -> event.dataPackRegistry(key, dataCodec.get(), networkCodec.get()));
    }

    @Override
    public void registerAttributes(DeferredEntity<? extends LivingEntity> type, AttributeSupplier.Builder builder)
    {
        NeoFragmentum.eventBus(modId).addListener((final EntityAttributeCreationEvent event) -> event.put(type.get(), builder.build()));
    }

    private <T> Lazy<Holder<T>> registerInternal(ResourceKey<? extends Registry<T>> key, ResourceLocation id, Supplier<? extends T> supplier)
    {
        final var deferredRegister = DeferredRegister.create(key, modId);
        deferredRegister.register(NeoFragmentum.eventBus(modId));
        return Lazy.value(deferredRegister.register(id.getPath(), supplier));
    }
}
