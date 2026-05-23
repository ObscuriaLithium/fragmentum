package dev.obscuria.fragmentum.fabric.registry;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.v2.api.common.registry.*;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
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
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

@SuppressWarnings("all")
public record FabricRegistrar(String modId) implements Registrar {

    @Override public <T, V extends T> Deferred<T> register(Registry<T> registry, Identifier id, Supplier<V> supplier) {
        return Deferred.create(Registry.registerForHolder(registry, id, supplier.get()));
    }

    @Override
    public <T, V extends T> Deferred<T> register(ResourceKey<? extends Registry<T>> registryKey, Identifier id, Supplier<V> supplier) {
        @Nullable var registry = BuiltInRegistries.REGISTRY.getValue(registryKey.identifier());
        if (registry == null) throw new IllegalStateException("No registry found for id " + id);
        return register((Registry<T>) registry, id, supplier);
    }

    @Override public <T extends Item> DeferredItem<T> registerItem(Identifier id, Supplier<T> supplier) {
        return DeferredItem.create(Registry.registerForHolder(BuiltInRegistries.ITEM, id, supplier.get()));
    }

    @Override public <T extends Block> DeferredBlock<T> registerBlock(Identifier id, Supplier<T> supplier) {
        return DeferredBlock.create(Registry.registerForHolder(BuiltInRegistries.BLOCK, id, supplier.get()));
    }

    @Override public DeferredAttribute registerAttribute(Identifier id, Supplier<Attribute> supplier) {
        return DeferredAttribute.create(Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, id, supplier.get()));
    }

    @Override
    public <T extends Entity> DeferredEntity<T> registerEntity(Identifier id, Supplier<EntityType<T>> supplier) {
        return DeferredEntity.create(Registry.registerForHolder(BuiltInRegistries.ENTITY_TYPE, id, supplier.get()));
    }

    @Override
    public <T extends BlockEntity> DeferredBlockEntity<T> registerBlockEntity(Identifier id, Supplier<BlockEntityType<T>> supplier) {
        return DeferredBlockEntity.create(Registry.registerForHolder(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, supplier.get()));
    }

    @Override
    public <T extends ParticleOptions> DeferredParticle<T> registerParticle(Identifier id, Supplier<ParticleType<T>> supplier) {
        return DeferredParticle.create(Registry.registerForHolder(BuiltInRegistries.PARTICLE_TYPE, id, supplier.get()));
    }

    @Override public <T> Registry<T> createRegistry(ResourceKey<Registry<T>> registryKey) {
        return FabricRegistryBuilder.create(registryKey).attribute(RegistryAttribute.SYNCED).buildAndRegister();
    }

    @Override public <T> void createDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec) {
        DynamicRegistries.register(registryKey, codec.get());
    }

    @Override public <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec) {
        DynamicRegistries.registerSynced(registryKey, codec.get());
    }

    @Override
    public <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec, Supplier<Codec<T>> networkCodec) {
        DynamicRegistries.registerSynced(registryKey, codec.get(), networkCodec.get());
    }

    @Override
    public void registerAttributes(DeferredEntity<? extends LivingEntity> entity, AttributeSupplier.Builder builder) {
        FabricDefaultAttributeRegistry.register(entity.get(), builder);
    }
}
