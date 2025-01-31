package dev.obscuria.fragmentum.api;

import net.minecraft.core.Holder;
import org.apache.logging.log4j.util.Lazy;

import java.util.function.Supplier;

/**
 * A utility class that wraps a {@link Lazy} holder of a specific type.
 * This class provides deferred access to a value of type {@code T},
 * ensuring that the value is only resolved when needed.
 *
 * @param <T> The base type of the value stored in the {@link Holder}.
 * @param <V> The specific subtype of {@code T} that this class operates on.
 */
public class Deferred<T, V extends T> implements Supplier<V>
{
    private final Lazy<Holder<T>> lazyHolder;

    /**
     * Constructs a new {@code Deferred} instance with the given {@link Lazy} holder.
     *
     * @param lazyHolder A {@link Lazy} holder that provides deferred access to a {@link Holder} of {@code T}.
     */
    public Deferred(Lazy<Holder<T>> lazyHolder)
    {
        this.lazyHolder = lazyHolder;
    }

    /**
     * Retrieves the {@link Holder} containing the value of type {@code T}.
     *
     * @return The {@link Holder} associated with this {@code Deferred}.
     */
    public Holder<T> holder()
    {
        return this.lazyHolder.get();
    }

    /**
     * Retrieves the actual value of type {@code V} stored in the {@link Holder}.
     *
     * @return The value of type {@code V}.
     * @throws ClassCastException If the value cannot be cast to type {@code V}.
     */
    @SuppressWarnings("unchecked")
    public V value()
    {
        return (V) holder().value();
    }

    /**
     * Retrieves the value of type {@code V}. This method is equivalent to {@link #value()}.
     *
     * @return The value of type {@code V}.
     */
    @Override
    public V get()
    {
        return value();
    }
}
