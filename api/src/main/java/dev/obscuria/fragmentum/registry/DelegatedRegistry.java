package dev.obscuria.fragmentum.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public interface DelegatedRegistry<T>
{
    ResourceKey<? extends Registry<T>> key();

    ResourceLocation name();

    Codec<T> byNameCodec();

    Codec<Holder<T>> holderByNameCodec();

    Set<ResourceLocation> keySet();

    Set<Map.Entry<ResourceKey<T>, T>> entrySet();

    boolean containsKey(ResourceLocation key);

    @Nullable T get(ResourceLocation key);

    @Nullable Holder.Reference<T> getHolder(ResourceLocation key);

    @Nullable ResourceLocation getKey(Holder<T> holder);

    @Nullable ResourceLocation getKey(T value);
}
