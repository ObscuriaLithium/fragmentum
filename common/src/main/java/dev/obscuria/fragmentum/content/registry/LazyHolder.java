package dev.obscuria.fragmentum.content.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public interface LazyHolder<T> {

    static <T> LazyHolder<T> create(ResourceKey<? extends Registry<T>> registryKey,
                                    Supplier<RegistryAccess> registryAccess,
                                    ResourceLocation id) {
        return new Instance<>(registryKey, registryAccess, id);
    }

    @Nullable Holder<T> get();

    Holder<T> getOrThrow();

    class Instance<T> implements LazyHolder<T> {

        private final ResourceKey<? extends Registry<T>> registryKey;
        private final Supplier<RegistryAccess> registryAccess;
        private final ResourceLocation id;

        private @Nullable Holder<T> holder;

        public Instance(ResourceKey<? extends Registry<T>> registryKey,
                        Supplier<RegistryAccess> registryAccess,
                        ResourceLocation id) {

            this.registryKey = registryKey;
            this.registryAccess = registryAccess;
            this.id = id;
        }

        @Override
        public Holder<T> getOrThrow() {
            if (holder != null) return holder;
            final var resourceKey = ResourceKey.create(registryKey, id);
            holder = registryAccess.get().registryOrThrow(registryKey).getHolderOrThrow(resourceKey);
            return holder;
        }

        @Override
        public @Nullable Holder<T> get() {
            try {
                return getOrThrow();
            } catch (Exception ignored) {
                return null;
            }
        }
    }
}
