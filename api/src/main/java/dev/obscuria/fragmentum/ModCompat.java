package dev.obscuria.fragmentum;

import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class ModCompat
{
    public ModCompat(String modId) {}

    public ModCompat(String modId, String modName) {}

    public String modId()
    {
        throw new NotImplementedException();
    }

    public String ModName()
    {
        throw new NotImplementedException();
    }

    public boolean isLoaded()
    {
        throw new NotImplementedException();
    }

    <T> @Nullable T getIfLoaded(Supplier<Supplier<T>> supplier)
    {
        throw new NotImplementedException();
    }

    void runIfLoaded(Supplier<Runnable> runnable)
    {
        throw new NotImplementedException();
    }

    void runIfMissing(Runnable runnable)
    {
        throw new NotImplementedException();
    }
}
