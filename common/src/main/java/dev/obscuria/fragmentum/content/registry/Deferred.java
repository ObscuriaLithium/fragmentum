package dev.obscuria.fragmentum.content.registry;

import com.google.common.base.Suppliers;
import net.minecraft.core.Holder;

import java.util.function.Supplier;

public class Deferred<T, V extends T> implements HolderProvider<T>, ValueProvider<V> {

    private final Supplier<Holder<T>> supplier;

    public Deferred(Supplier<Holder<T>> supplier) {
        this.supplier = Suppliers.memoize(supplier::get);
    }

    @Override
    public Holder<T> holder() {
        return supplier.get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get() {
        return (V) holder().value();
    }
}
