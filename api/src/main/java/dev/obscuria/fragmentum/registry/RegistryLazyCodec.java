package dev.obscuria.fragmentum.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

public interface RegistryLazyCodec<E> extends Codec<LazyHolder<E>> {

    static <E> RegistryLazyCodec<E> create(
            ResourceKey<? extends Registry<E>> registryKey,
            Supplier<RegistryAccess> registryAccess) {
        throw new NotImplementedException();
    }
}