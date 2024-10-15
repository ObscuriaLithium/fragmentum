package dev.obscuria.fragmentum.api;

import com.google.common.base.Suppliers;
import net.minecraft.core.Holder;

import java.util.function.Supplier;

public final class Deferred<T, V extends T>
{
    private final Supplier<Holder<T>> cachedHolder;

    public static <T, V extends T> Deferred<T, V>
    of(Supplier<Holder<T>> holderSupplier)
    {
        return new Deferred<>(holderSupplier);
    }

    public static <T, V extends T> Deferred<T, V>
    of(Holder<T> holder)
    {
        return new Deferred<>(() -> holder);
    }

    private Deferred(Supplier<Holder<T>> holderSupplier)
    {
        this.cachedHolder = Suppliers.memoize(holderSupplier::get);
    }

    public Holder<T> holder()
    {
        return this.cachedHolder.get();
    }

    @SuppressWarnings("unchecked")
    public V value()
    {
        return (V) holder().value();
    }
}
