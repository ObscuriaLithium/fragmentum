package dev.obscuria.fragmentum.forge.service;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.api.Deferred;
import dev.obscuria.fragmentum.api.v1.common.IRegistrar;
import dev.obscuria.fragmentum.forge.ForgeFragmentum;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DataPackRegistryEvent;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public record ForgeRegistrar(String modId) implements IRegistrar
{
    @Override
    public <T, V extends T> Deferred<T, V> register(Registry<T> registry,
                                                    ResourceLocation id,
                                                    Supplier<V> valueSupplier)
    {
        return this.register(registry, ResourceKey.create(registry.key(), id), valueSupplier);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, V extends T> Deferred<T, V> register(Registry<T> registry,
                                                    ResourceKey<T> key,
                                                    Supplier<V> valueSupplier)
    {
        final var deferredRegister = DeferredRegister.create(registry.key(), modId);
        ForgeFragmentum.register(modId, deferredRegister);
        final var object = deferredRegister.register(key.location().getPath(), valueSupplier);
        return Deferred.of(() -> (Holder<T>) object.getHolder().orElseThrow());
    }

    @Override
    public <T> void newDataRegistry(ResourceKey<Registry<T>> key,
                                    Codec<T> codec)
    {
        ForgeFragmentum.addListener(modId, (final DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(key, codec));
    }

    @Override
    public <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key,
                                          Codec<T> codec)
    {
        ForgeFragmentum.addListener(modId, (final DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(key, codec, codec));
    }

    @Override
    public <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key,
                                          Codec<T> dataCodec,
                                          Codec<T> networkCodec)
    {
        ForgeFragmentum.addListener(modId, (final DataPackRegistryEvent.NewRegistry event) ->
                event.dataPackRegistry(key, dataCodec, networkCodec));
    }
}
