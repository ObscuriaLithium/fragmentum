package dev.obscuria.fragmentum.v2.api.common.signal;

import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public interface Signal<T> {

    default void connect(T listener) {
        connect(null, false, null, listener);
    }

    default void connect(@Nullable Object source, T listener) {
        connect(source, false, null, listener);
    }

    default void connect(boolean oneShot, T listener) {
        connect(null, false, null, listener);
    }

    default void connect(@Nullable Object source, boolean oneShot, T listener) {
        connect(source, oneShot, null, listener);
    }

    void connect(@Nullable Object source, boolean oneShot, @Nullable Signal0 breaker, T listener);

    void disconnect(Object source);
}
