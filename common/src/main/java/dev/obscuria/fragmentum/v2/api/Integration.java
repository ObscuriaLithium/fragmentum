package dev.obscuria.fragmentum.v2.api;

import dev.obscuria.fragmentum.v2.core._Integration;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface Integration {

    static Integration create(String modId, String modName) {
        return new _Integration(modId, modName);
    }

    static Integration create(String modId) {
        return create(modId, modId);
    }

    String modId();

    String modName();

    boolean isLoaded();

    <T> @Nullable T getIfLoaded(Supplier<Supplier<T>> supplier);

    void runIfLoaded(Supplier<Runnable> runnable);

    void runIfMissing(Runnable runnable);
}
