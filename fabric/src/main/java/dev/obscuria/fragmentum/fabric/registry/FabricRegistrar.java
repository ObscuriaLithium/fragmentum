package dev.obscuria.fragmentum.fabric.registry;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.content.registry.*;
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
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public record FabricRegistrar(String modId) implements Registrar {

    @Override
    public <R, T extends R> Deferred<T> register(Registry<R> registry, Identifier id, Supplier<T> supplier) {
        return new Deferred<>(Registry.registerForHolder(registry, id, supplier.get()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, T extends R> Deferred<T> register(ResourceKey<? extends Registry<R>> registryKey, Identifier id, Supplier<T> supplier) {
        return register((Registry<R>) BuiltInRegistries.REGISTRY.getValue(registryKey.identifier()), id, supplier);
    }

    @Override
    public <T extends Entity> DeferredEntity<T> registerEntity(Identifier id, Supplier<EntityType<T>> supplier) {
        return new DeferredEntity<>(Registry.registerForHolder(BuiltInRegistries.ENTITY_TYPE, id, supplier.get()));
    }

    @Override
    public <T extends BlockEntity> DeferredBlockEntity<T> registerBlockEntity(Identifier id, Supplier<BlockEntityType<T>> supplier) {
        return new DeferredBlockEntity<>(Registry.registerForHolder(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, supplier.get()));
    }

    @Override
    public <T extends ParticleOptions> DeferredParticle<T> registerParticle(Identifier id, Supplier<ParticleType<T>> supplier) {
        return new DeferredParticle<>(Registry.registerForHolder(BuiltInRegistries.PARTICLE_TYPE, id, supplier.get()));
    }

    @Override
    public <T> Registry<T> createRegistry(ResourceKey<Registry<T>> registryKey) {
        return FabricRegistryBuilder
                .createSimple(registryKey)
                .attribute(RegistryAttribute.SYNCED)
                .buildAndRegister();
    }

    @Override
    public <T> void createDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec) {
        DynamicRegistries.register(registryKey, codec.get());
    }

    @Override
    public <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec) {
        DynamicRegistries.registerSynced(registryKey, codec.get());
    }

    @Override
    public <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec, Supplier<Codec<T>> networkCodec) {
        DynamicRegistries.registerSynced(registryKey, codec.get(), networkCodec.get());
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public void registerAttributes(DeferredEntity<? extends LivingEntity> entity, AttributeSupplier.Builder builder) {
        FabricDefaultAttributeRegistry.register(entity.get(), builder);
    }
}
