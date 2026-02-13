package dev.obscuria.fragmentum.content.registry;

import net.minecraft.core.Holder;

import java.util.function.Supplier;

public class Deferred<T> implements Supplier<T> {

    private final Holder<T> holder;

    public Deferred(Holder<T> holder) {
        this.holder = holder;
    }

    public Holder<T> holder() {
        return holder;
    }

    @Override
    public T get() {
        return holder.value();
    }
}
