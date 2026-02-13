package dev.obscuria.fragmentum.content.registry;

import net.minecraft.core.Holder;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

public class Deferred<T> implements Supplier<T> {

    public Deferred(Holder<T> holder) {
        throw new NotImplementedException();
    }

    public Holder<T> holder() {
        throw new NotImplementedException();
    }

    @Override
    public T get() {
        throw new NotImplementedException();
    }
}
