package dev.obscuria.fragmentum.api.v1.common;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.api.Deferred;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public interface IRegistrar
{
    <T, V extends T> Deferred<T, V> register(Registry<T> registry,
                                             ResourceLocation id,
                                             Supplier<V> valueSupplier);

    <T, V extends T> Deferred<T, V> register(Registry<T> registry,
                                             ResourceKey<T> key,
                                             Supplier<V> valueSupplier);

    <T> void newDataRegistry(ResourceKey<Registry<T>> key,
                             Codec<T> codec);

    <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key,
                                   Codec<T> codec);

    <T> void newSyncedDataRegistry(ResourceKey<Registry<T>> key,
                                   Codec<T> dataCodec,
                                   Codec<T> networkCodec);
}
