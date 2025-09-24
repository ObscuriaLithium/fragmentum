package dev.obscuria.fragmentum.fabric.registry;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.registry.*;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public record FabricRegistrar(String modId) implements Registrar
{
    @Override
    public <T, V extends T> Deferred<T, V> register(Registry<T> registry, ResourceLocation id, Supplier<V> supplier)
    {
        final var holder = Registry.registerForHolder(registry, id, supplier.get());
        return new Deferred<>(() -> holder);
    }

    @Override
    @SuppressWarnings("ALL")
    public <T, V extends T> Deferred<T, V> register(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation id, Supplier<V> supplier)
    {
        return register((Registry<T>) BuiltInRegistries.REGISTRY.get(registryKey.location()), id, supplier);
    }

    @Override
    public <T extends Item> DeferredItem<T> registerItem(ResourceLocation id, Supplier<T> supplier)
    {
        final var holder = Registry.registerForHolder(BuiltInRegistries.ITEM, id, supplier.get());
        return new DeferredItem<>(() -> holder);
    }

    @Override
    public <T extends Block> DeferredBlock<T> registerBlock(ResourceLocation id, Supplier<T> supplier)
    {
        final var holder = Registry.registerForHolder(BuiltInRegistries.BLOCK, id, supplier.get());
        return new DeferredBlock<>(() -> holder);
    }

    @Override
    public <T extends Entity> DeferredEntity<T> registerEntity(ResourceLocation id, Supplier<EntityType<T>> supplier)
    {
        final var holder = Registry.registerForHolder(BuiltInRegistries.ENTITY_TYPE, id, supplier.get());
        return new DeferredEntity<>(() -> holder);
    }

    @Override
    public <T extends BlockEntity> DeferredBlockEntity<T> registerBlockEntity(ResourceLocation id, Supplier<BlockEntityType<T>> supplier)
    {
        final var holder = Registry.registerForHolder(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, supplier.get());
        return new DeferredBlockEntity<>(() -> holder);
    }

    @Override
    public <T extends MobEffect> DeferredMobEffect<T> registerMobEffect(ResourceLocation id, Supplier<MobEffect> supplier)
    {
        final var holder = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, id, supplier.get());
        return new DeferredMobEffect<>(() -> holder);
    }

    @Override
    public <T extends ParticleOptions> DeferredParticle<T> registerParticle(ResourceLocation id, Supplier<ParticleType<T>> supplier)
    {
        final var holder = Registry.registerForHolder(BuiltInRegistries.PARTICLE_TYPE, id, supplier.get());
        return new DeferredParticle<>(() -> holder);
    }

    @Override
    public DeferredAttribute registerAttribute(ResourceLocation id, Supplier<Attribute> supplier)
    {
        final var holder = Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, id, supplier.get());
        return new DeferredAttribute(() -> holder);
    }

    @Override
    public <T> DelegatedRegistry<T> createRegistry(ResourceKey<Registry<T>> registryKey)
    {
        return new FabricDelegatedRegistry<>(
                FabricRegistryBuilder
                        .createSimple(registryKey)
                        .attribute(RegistryAttribute.SYNCED)
                        .buildAndRegister());
    }

    @Override
    public <T> void createDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec)
    {
        DynamicRegistries.register(registryKey, codec.get());
    }

    @Override
    public <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec)
    {
        DynamicRegistries.registerSynced(registryKey, codec.get());
    }

    @Override
    public <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec, Supplier<Codec<T>> networkCodec)
    {
        DynamicRegistries.registerSynced(registryKey, codec.get(), networkCodec.get());
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public void registerAttributes(DeferredEntity<? extends LivingEntity> entity, AttributeSupplier.Builder builder)
    {
        FabricDefaultAttributeRegistry.register(entity.get(), builder);
    }
}
