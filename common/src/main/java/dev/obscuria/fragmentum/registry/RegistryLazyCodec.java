package dev.obscuria.fragmentum.registry;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public interface RegistryLazyCodec<E> extends Codec<LazyHolder<E>> {

    static <E> RegistryLazyCodec<E> create(
            ResourceKey<? extends Registry<E>> registryKey,
            Supplier<RegistryAccess> registryAccess) {
        return new Instance<>(registryKey, registryAccess);
    }

    record Instance<E>(
            ResourceKey<? extends Registry<E>> registryKey,
            Supplier<RegistryAccess> registryAccess
    ) implements RegistryLazyCodec<E> {

        @Override
        public <T> DataResult<T> encode(LazyHolder<E> holder, DynamicOps<T> ops, T value) {
            return holder.getOrThrow().unwrap().map(
                    key -> ResourceLocation.CODEC.encode(key.location(), ops, value),
                    element -> DataResult.error(() -> "Elements from registry %s can't be serialized to a value".formatted(this.registryKey)));
        }

        @Override
        public <T> DataResult<Pair<LazyHolder<E>, T>> decode(DynamicOps<T> ops, T value) {
            return ResourceLocation.CODEC.decode(ops, value).flatMap(pair -> DataResult
                    .success(Pair.of(LazyHolder.create(registryKey, registryAccess, pair.getFirst()), pair.getSecond())));
        }

        public String toString() {
            return "RegistryLazyCodec[" + this.registryKey + "]";
        }
    }
}