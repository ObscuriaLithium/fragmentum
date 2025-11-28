package dev.obscuria.fragmentum.fabric.registry;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.content.registry.DelegatedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

public record FabricDelegatedRegistry<T>(Registry<T> source) implements DelegatedRegistry<T> {

    @Override
    public ResourceKey<? extends Registry<T>> key() {
        return source.key();
    }

    @Override
    public ResourceLocation name() {
        return source.key().registry();
    }

    @Override
    public Codec<T> byNameCodec() {
        return source.byNameCodec();
    }

    @Override
    public Codec<Holder<T>> holderByNameCodec() {
        return source.holderByNameCodec();
    }

    @Override
    public Set<ResourceLocation> keySet() {
        return source.keySet();
    }

    @Override
    public Set<Map.Entry<ResourceKey<T>, T>> entrySet() {
        return source.entrySet();
    }

    @Override
    public boolean containsKey(ResourceLocation key) {
        return source.containsKey(key);
    }

    @Override
    public @Nullable T get(ResourceLocation key) {
        return source.get(key);
    }

    @Override
    public Holder.@Nullable Reference<T> getHolder(ResourceLocation key) {
        return source.getHolder(ResourceKey.create(key(), key)).orElse(null);
    }

    @Override
    public @Nullable ResourceLocation getKey(Holder<T> holder) {
        return holder.unwrap().right().map(source::getKey).orElse(null);
    }

    @Override
    public @Nullable ResourceLocation getKey(T value) {
        return source.getKey(value);
    }
}
