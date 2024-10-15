package dev.obscuria.fragmentum.api;

import net.minecraft.core.Holder;

public record Deferred<T, V extends T>(Holder<T> holder)
{
    @SuppressWarnings("unchecked")
    public V value()
    {
        return (V) holder.value();
    }
}
