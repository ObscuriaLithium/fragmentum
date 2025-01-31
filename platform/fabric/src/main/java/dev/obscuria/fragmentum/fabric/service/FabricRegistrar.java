package dev.obscuria.fragmentum.fabric.service;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.api.*;
import dev.obscuria.fragmentum.api.v1.common.IRegistrar;
import dev.obscuria.fragmentum.api.v1.common.UnifiedRegistry;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
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
import org.apache.logging.log4j.util.Lazy;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

@ApiStatus.Internal
public record FabricRegistrar(String modId) implements IRegistrar
{
    @Override
    @SuppressWarnings("unchecked")
    public <T, V extends T> Deferred<T, V> register(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation id, Supplier<V> supplier)
    {
        return register((Registry<T>) BuiltInRegistries.REGISTRY.get(registryKey.location()), id, supplier);
    }

    @Override
    public <T, V extends T> Deferred<T, V> register(Registry<T> registry, ResourceLocation id, Supplier<V> supplier)
    {
        return new Deferred<>(Lazy.value(Registry.registerForHolder(registry, id, supplier.get())));
    }

    @Override
    public <T extends Item> DeferredItem<T> registerItem(ResourceLocation id, Supplier<T> supplier)
    {
        return new DeferredItem<>(Lazy.value(Registry.registerForHolder(BuiltInRegistries.ITEM, id, supplier.get())));
    }

    @Override
    public DeferredAttribute registerAttribute(ResourceLocation id, Supplier<Attribute> supplier)
    {
        return new DeferredAttribute(Lazy.value(Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, id, supplier.get())));
    }

    @Override
    public <T extends Entity> DeferredEntity<T> registerEntity(ResourceLocation id, Supplier<EntityType<T>> supplier)
    {
        return new DeferredEntity<>(Lazy.value(Registry.registerForHolder(BuiltInRegistries.ENTITY_TYPE, id, supplier.get())));
    }

    @Override
    public <T extends BlockEntity> DeferredBlockEntity<T> registerBlockEntity(ResourceLocation id, Supplier<BlockEntityType<T>> supplier)
    {
        return new DeferredBlockEntity<>(Lazy.value(Registry.registerForHolder(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, supplier.get())));
    }

    @Override
    public <T extends MobEffect> DeferredMobEffect<T> registerMobEffect(ResourceLocation id, Supplier<T> supplier)
    {
        return new DeferredMobEffect<>(Lazy.value(Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, id, supplier.get())));
    }

    @Override
    public <T extends ParticleOptions> DeferredParticle<T> registerParticle(ResourceLocation id, Supplier<ParticleType<T>> supplier)
    {
        return new DeferredParticle<>(Lazy.value(Registry.registerForHolder(BuiltInRegistries.PARTICLE_TYPE, id, supplier.get())));
    }

    @Override
    public <T> UnifiedRegistry<T> newRegistry(ResourceKey<Registry<T>> key)
    {
        return new FabricUnifiedRegistry<>(FabricRegistryBuilder.createSimple(key).attribute(RegistryAttribute.SYNCED).buildAndRegister());
    }

    @Override
    public <T> void newDataRegistry(ResourceKey<Registry<T>> key, Supplier<Codec<T>> codec)
    {
        DynamicRegistries.register(key, codec.get());
    }

    @Override
    public <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key, Supplier<Codec<T>> codec)
    {
        DynamicRegistries.registerSynced(key, codec.get());
    }

    @Override
    public <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key, Supplier<Codec<T>> dataCodec, Supplier<Codec<T>> networkCodec)
    {
        DynamicRegistries.registerSynced(key, dataCodec.get(), networkCodec.get());
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public void registerAttributes(DeferredEntity<? extends LivingEntity> type, AttributeSupplier.Builder builder)
    {
        FabricDefaultAttributeRegistry.register(type.get(), builder);
    }
}
