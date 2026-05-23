package dev.obscuria.fragmentum.v2.core;

import dev.obscuria.fragmentum.Fragmentum;
import dev.obscuria.fragmentum.v2.api.Integration;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.function.Supplier;

public record _Integration(String modId, String modName) implements Integration {

    @Override
    public boolean isLoaded() {
        return Fragmentum.PLATFORM.isModLoaded(modId);
    }

    @Override
    public <T> @Nullable T getIfLoaded(Supplier<Supplier<T>> supplier) {
        if (!isLoaded()) return null;
        return supplier.get().get();
    }

    @Override
    public void runIfLoaded(Supplier<Runnable> runnable) {
        if (!isLoaded()) return;
        runnable.get().run();
    }

    @Override
    public void runIfMissing(Runnable runnable) {
        if (isLoaded()) return;
        runnable.run();
    }
}
