package dev.obscuria.fragmentum.util.signal;

import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public abstract class Signal<T>
{
    public void connect(T listener)
    {
        throw new NotImplementedException();
    }

    public void connect(@Nullable Object source, T listener)
    {
        throw new NotImplementedException();
    }

    public void connect(boolean oneShot, T listener)
    {
        throw new NotImplementedException();
    }

    public void connect(@Nullable Object source, boolean oneShot, T listener)
    {
        throw new NotImplementedException();
    }

    public void connect(@Nullable Object source, boolean oneShot, @Nullable Signal0 breaker, T listener)
    {
        throw new NotImplementedException();
    }

    public void disconnect(Object source)
    {
        throw new NotImplementedException();
    }
}
