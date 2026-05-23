package dev.obscuria.fragmentum.v2.core.common.registry;

import dev.obscuria.fragmentum.v2.api.common.registry.Deferred;
import net.minecraft.core.Holder;

public class _Deferred<T> implements Deferred<T> {

    protected final Holder<T> holder;

    public _Deferred(Holder<T> holder) {
        this.holder = holder;
    }

    @Override public Holder<T> holder() {
        return holder;
    }

    @Override public T get() {
        return holder().value();
    }
}