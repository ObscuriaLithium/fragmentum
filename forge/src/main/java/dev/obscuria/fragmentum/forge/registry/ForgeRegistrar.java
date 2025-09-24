package dev.obscuria.fragmentum.forge.registry;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.forge.ForgeFragmentum;
import dev.obscuria.fragmentum.registry.*;
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
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DataPackRegistryEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public record ForgeRegistrar(String modId) implements Registrar
{
    @Override
    public <T, V extends T> Deferred<T, V> register(Registry<T> registry, ResourceLocation id, Supplier<V> supplier)
    {
        return register(registry.key(), id, supplier);
    }

    @Override
    public <T, V extends T> Deferred<T, V> register(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation id, Supplier<V> supplier)
    {
        return new Deferred<>(this.registerInternal(registryKey, id, supplier));
    }

    @Override
    public <T extends Item> DeferredItem<T> registerItem(ResourceLocation id, Supplier<T> supplier)
    {
        return new DeferredItem<>(this.registerInternal(Registries.ITEM, id, supplier));
    }

    @Override
    public <T extends Block> DeferredBlock<T> registerBlock(ResourceLocation id, Supplier<T> supplier)
    {
        return new DeferredBlock<>(this.registerInternal(Registries.BLOCK, id, supplier));
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
    public <T extends MobEffect> DeferredMobEffect<T> registerMobEffect(ResourceLocation id, Supplier<MobEffect> supplier)
    {
        return new DeferredMobEffect<>(this.registerInternal(Registries.MOB_EFFECT, id, supplier));
    }

    @Override
    public <T extends ParticleOptions> DeferredParticle<T> registerParticle(ResourceLocation id, Supplier<ParticleType<T>> supplier)
    {
        return new DeferredParticle<>(this.registerInternal(Registries.PARTICLE_TYPE, id, supplier));
    }

    @Override
    public DeferredAttribute registerAttribute(ResourceLocation id, Supplier<Attribute> supplier)
    {
        return new DeferredAttribute(this.registerInternal(Registries.ATTRIBUTE, id, supplier));
    }

    @Override
    public <T> DelegatedRegistry<T> createRegistry(ResourceKey<Registry<T>> registryKey)
    {
        final var registry = new ForgeDelegatedRegistry<>(registryKey);
        ForgeFragmentum.addListener(modId, (final NewRegistryEvent event) -> event.create(RegistryBuilder.of(registryKey.location()), registry::bind));
        return registry;
    }

    @Override
    public <T> void createDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec)
    {
        ForgeFragmentum.addListener(modId, (final DataPackRegistryEvent.NewRegistry event) -> event.dataPackRegistry(registryKey, codec.get()));
    }

    @Override
    public <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec)
    {
        ForgeFragmentum.addListener(modId, (final DataPackRegistryEvent.NewRegistry event) -> event.dataPackRegistry(registryKey, codec.get(), codec.get()));
    }

    @Override
    public <T> void createSyncedDataRegistry(ResourceKey<Registry<T>> registryKey, Supplier<Codec<T>> codec, Supplier<Codec<T>> networkCodec)
    {
        ForgeFragmentum.addListener(modId, (final DataPackRegistryEvent.NewRegistry event) -> event.dataPackRegistry(registryKey, codec.get(), networkCodec.get()));
    }

    @Override
    public void registerAttributes(DeferredEntity<? extends LivingEntity> entity, AttributeSupplier.Builder builder)
    {
        ForgeFragmentum.addListener(modId, (final EntityAttributeCreationEvent event) -> event.put(entity.get(), builder.build()));
    }

    @SuppressWarnings("unchecked")
    private <T> Lazy<Holder<T>> registerInternal(ResourceKey<? extends Registry<T>> key, ResourceLocation id, Supplier<? extends T> supplier)
    {
        final var deferredRegister = DeferredRegister.create(key, modId);
        ForgeFragmentum.register(modId, deferredRegister);
        final var object = deferredRegister.register(id.getPath(), supplier);
        return Lazy.of(() -> (Holder<T>) object.getHolder().orElseThrow());
    }
}
