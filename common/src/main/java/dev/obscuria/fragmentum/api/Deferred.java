package dev.obscuria.fragmentum.api;

import net.minecraft.core.Holder;
import org.apache.logging.log4j.util.Lazy;

import java.util.function.Supplier;

public final class Deferred<T, V extends T>
{
    private final Lazy<Holder<T>> lazyHolder;

    public static <T, V extends T> Deferred<T, V>
    of(Supplier<Holder<T>> holderSupplier)
    {
        return new Deferred<>(Lazy.lazy(holderSupplier));
    }

    public static <T, V extends T> Deferred<T, V>
    of(Holder<T> holder)
    {
        return new Deferred<>(Lazy.value(holder));
    }

    private Deferred(Lazy<Holder<T>> lazyHolder)
    {
        this.lazyHolder = lazyHolder;
    }

    public Holder<T> holder()
    {
        return this.lazyHolder.get();
    }

    @SuppressWarnings("unchecked")
    public V value()
    {
        return (V) holder().value();
    }
}
