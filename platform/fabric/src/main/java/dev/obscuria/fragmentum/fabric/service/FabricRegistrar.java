package dev.obscuria.fragmentum.fabric.service;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.api.Deferred;
import dev.obscuria.fragmentum.api.v1.common.IRegistrar;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public record FabricRegistrar(String modId) implements IRegistrar
{
    @Override
    public <T, V extends T> Deferred<T, V> register(Registry<T> registry,
                                                    ResourceLocation id,
                                                    Supplier<V> valueSupplier)
    {
        return this.register(registry, ResourceKey.create(registry.key(), id), valueSupplier);
    }

    @Override
    public <T, V extends T> Deferred<T, V> register(Registry<T> registry,
                                                    ResourceKey<T> key,
                                                    Supplier<V> valueSupplier)
    {
        return Deferred.of(Registry.registerForHolder(registry, key, valueSupplier.get()));
    }

    @Override
    public <T> void newDataRegistry(ResourceKey<Registry<T>> key,
                                    Codec<T> codec)
    {
        DynamicRegistries.register(key, codec);
    }

    @Override
    public <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key,
                                          Codec<T> codec)
    {
        DynamicRegistries.registerSynced(key, codec);
    }

    @Override
    public <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key,
                                          Codec<T> dataCodec,
                                          Codec<T> networkCodec)
    {
        DynamicRegistries.registerSynced(key, dataCodec, networkCodec);
    }
}
