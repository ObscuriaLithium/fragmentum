package dev.obscuria.fragmentum.v2.api.common.registry;

import dev.obscuria.fragmentum.v2.core.common.registry._Deferred;
import net.minecraft.core.Holder;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface Deferred<T> extends Supplier<T> {

    static <T> Deferred<T> create(Holder<T> holder) {
        return new _Deferred<>(holder);
    }

    Holder<T> holder();
}
