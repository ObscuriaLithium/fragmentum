package dev.obscuria.fragmentum.registry;

import net.minecraft.core.Holder;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class Deferred<T, V extends T> implements HolderProvider<T>, ValueProvider<V>
{
    public Deferred(Supplier<Holder<T>> supplier) {}

    @Override
    public Holder<T> holder()
    {
        throw new NotImplementedException();
    }

    @Override
    public V get()
    {
        throw new NotImplementedException();
    }
}
