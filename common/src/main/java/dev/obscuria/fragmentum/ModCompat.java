package dev.obscuria.fragmentum;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class ModCompat
{
    private final String modId;
    private final String modName;

    public ModCompat(String modId)
    {
        this(modId, modId);
    }

    public ModCompat(String modId, String modName)
    {
        this.modId = modId;
        this.modName = modName;
    }

    public String modId()
    {
        return modId;
    }

    public String ModName()
    {
        return modName;
    }

    public boolean isLoaded()
    {
        return Fragmentum.PLATFORM.isModLoaded(modId);
    }

    <T> @Nullable T getIfLoaded(Supplier<Supplier<T>> supplier)
    {
        if (!isLoaded()) return null;
        return supplier.get().get();
    }

    void runIfLoaded(Supplier<Runnable> runnable)
    {
        if (!isLoaded()) return;
        runnable.get().run();
    }

    void runIfMissing(Runnable runnable)
    {
        if (isLoaded()) return;
        runnable.run();
    }
}
