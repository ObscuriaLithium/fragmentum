package dev.obscuria.fragmentum.api.v1.common;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.api.Deferred;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface ObscureRegistry
{
    static <T, V extends T> Deferred<T, V> register(String modId,
                                                    Registry<T> registry,
                                                    ResourceLocation id,
                                                    Supplier<V> valueSupplier)
    {
        return V1Common.INSTANCE.register(modId, registry, ResourceKey.create(registry.key(), id), valueSupplier);
    }

    static <T, V extends T> Deferred<T, V> register(String modId,
                                                    Registry<T> registry,
                                                    ResourceKey<T> key,
                                                    Supplier<V> valueSupplier)
    {
        return V1Common.INSTANCE.register(modId, registry, key, valueSupplier);
    }

    static <T> void newDataRegistry(String modId,
                                    ResourceKey<Registry<T>> key,
                                    Codec<T> codec)
    {
        V1Common.INSTANCE.newDataRegistry(modId, key, codec);
    }

    static <T> void newSyncedDataRegistry(String modId,
                                          ResourceKey<Registry<T>> key,
                                          Codec<T> codec)
    {
        V1Common.INSTANCE.newSyncedDataRegistry(modId, key, codec);
    }


    static <T> void newSyncedDataRegistry(String modId,
                                          ResourceKey<Registry<T>> key,
                                          Codec<T> dataCodec,
                                          Codec<T> networkCodec)
    {
        V1Common.INSTANCE.newSyncedDataRegistry(modId, key, dataCodec, networkCodec);
    }
}
