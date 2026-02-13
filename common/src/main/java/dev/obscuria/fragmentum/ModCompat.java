package dev.obscuria.fragmentum;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public record ModCompat(String modId, String modName) {

    public ModCompat(String modId) {
        this(modId, modId);
    }

    public String ModName() {
        return modName;
    }

    public boolean isLoaded() {
        return Fragmentum.PLATFORM.isModLoaded(modId);
    }

    public <T> @Nullable T getIfLoaded(Supplier<Supplier<T>> supplier) {
        if (!isLoaded()) return null;
        return supplier.get().get();
    }

    public void runIfLoaded(Supplier<Runnable> runnable) {
        if (!isLoaded()) return;
        runnable.get().run();
    }

    public void runIfMissing(Runnable runnable) {
        if (isLoaded()) return;
        runnable.run();
    }
}
