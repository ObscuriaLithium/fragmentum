package dev.obscuria.fragmentum.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public interface LazyHolder<T> {

    static <T> LazyHolder<T> create(ResourceKey<? extends Registry<T>> registryKey,
                                    Supplier<RegistryAccess> registryAccess,
                                    ResourceLocation id) {
        throw new NotImplementedException();
    }

    @Nullable Holder<T> get();

    Holder<T> getOrThrow();
}
