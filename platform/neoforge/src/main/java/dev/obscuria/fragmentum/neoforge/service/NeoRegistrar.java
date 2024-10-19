package dev.obscuria.fragmentum.neoforge.service;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.api.Deferred;
import dev.obscuria.fragmentum.api.v1.common.IRegistrar;
import dev.obscuria.fragmentum.neoforge.NeoFragmentum;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public record NeoRegistrar(String modId) implements IRegistrar
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
        final var deferredRegister = DeferredRegister.create(registry, modId);
        deferredRegister.register(NeoFragmentum.eventBus(modId));
        return Deferred.of(deferredRegister.register(key.location().getPath(), valueSupplier));
    }

    @Override
    public <T> void newDataRegistry(ResourceKey<Registry<T>> key,
                                    Codec<T> codec)
    {
        NeoFragmentum.eventBus(modId).addListener((DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(key, codec));
    }

    @Override
    public <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key,
                                          Codec<T> codec)
    {
        NeoFragmentum.eventBus(modId).addListener((DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(key, codec, codec));
    }

    @Override
    public <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key,
                                          Codec<T> dataCodec,
                                          Codec<T> networkCodec)
    {
        NeoFragmentum.eventBus(modId).addListener((DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(key, dataCodec, networkCodec));
    }
}
