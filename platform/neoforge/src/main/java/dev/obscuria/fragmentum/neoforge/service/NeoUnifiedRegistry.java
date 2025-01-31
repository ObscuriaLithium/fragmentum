package dev.obscuria.fragmentum.neoforge.service;

import com.mojang.serialization.Codec;
import dev.obscuria.fragmentum.api.v1.common.UnifiedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@ApiStatus.Internal
public record NeoUnifiedRegistry<T>(Registry<T> source) implements UnifiedRegistry<T>
{
    @Override
    public ResourceKey<? extends Registry<T>> key()
    {
        return source.key();
    }

    @Override
    public ResourceLocation name()
    {
        return source.key().registry();
    }

    @Override
    public Codec<T> byNameCodec()
    {
        return source.byNameCodec();
    }

    @Override
    public Codec<Holder<T>> holderByNameCodec()
    {
        return source.holderByNameCodec();
    }

    @Override
    public Set<ResourceLocation> keySet()
    {
        return source.keySet();
    }

    @Override
    public Set<Map.Entry<ResourceKey<T>, T>> entrySet()
    {
        return source.entrySet();
    }

    @Override
    public boolean containsKey(ResourceLocation id)
    {
        return source.containsKey(id);
    }

    @Override
    public Optional<T> get(ResourceLocation id)
    {
        return source.getOptional(id);
    }

    @Override
    public Optional<Holder.Reference<T>> getHolder(ResourceLocation id)
    {
        return source.getHolder(id);
    }

    @Override
    public Optional<ResourceLocation> getKey(T value)
    {
        return Optional.ofNullable(source.getKey(value));
    }

    @Override
    public Optional<ResourceLocation> getKey(Holder<T> holder)
    {
        return holder.unwrap().right().flatMap(this::getKey);
    }
}
