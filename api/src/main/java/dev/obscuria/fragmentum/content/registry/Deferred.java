package dev.obscuria.fragmentum.content.registry;

import net.minecraft.core.Holder;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

public class Deferred<T, V extends T> implements HolderProvider<T>, ValueProvider<V> {

    public Deferred(Supplier<Holder<T>> supplier) {
        throw new NotImplementedException();
    }

    @Override
    public Holder<T> holder() {
        throw new NotImplementedException();
    }

    @Override
    public V get() {
        throw new NotImplementedException();
    }
}
