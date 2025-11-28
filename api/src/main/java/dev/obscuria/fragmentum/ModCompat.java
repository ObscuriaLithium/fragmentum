package dev.obscuria.fragmentum;

import org.apache.commons.lang3.NotImplementedException;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public record ModCompat(String modId, String modName) {

    public ModCompat(String modId) {
        this(modId, modId);
    }

    public String ModName() {
        throw new NotImplementedException();
    }

    public boolean isLoaded() {
        throw new NotImplementedException();
    }

    public <T> @Nullable T getIfLoaded(Supplier<Supplier<T>> supplier) {
        throw new NotImplementedException();
    }

    public void runIfLoaded(Supplier<Runnable> runnable) {
        throw new NotImplementedException();
    }

    public void runIfMissing(Runnable runnable) {
        throw new NotImplementedException();
    }
}
