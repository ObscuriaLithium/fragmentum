package dev.obscuria.fragmentum.core.v1.common;

import dev.obscuria.fragmentum.api.Fragmentum;
import dev.obscuria.fragmentum.api.v1.common.ModBridge;
import org.jetbrains.annotations.ApiStatus;

import java.util.Optional;
import java.util.function.Supplier;

@ApiStatus.Internal
public record BaseModBridge(String id, String displayName) implements ModBridge
{
    @Override
    public boolean isLoaded()
    {
        return Fragmentum.PLATFORM.isModLoaded(id);
    }

    @Override
    public <T> Optional<T> safeGet(Supplier<Supplier<T>> supplier)
    {
        return isLoaded()
                ? Optional.ofNullable(supplier.get().get())
                : Optional.empty();
    }

    @Override
    public void safeRun(Supplier<Runnable> supplier)
    {
        if (!isLoaded()) return;
        supplier.get().run();
    }

    @Override
    public void ifMissing(Runnable runnable)
    {
        if (isLoaded()) return;
        runnable.run();
    }
}
