package dev.obscuria.fragmentum.util.event;

import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("unused")
public final class Event<T>
{
    public EventToken register(T listener)
    {
        return register(1000, listener);
    }

    public EventToken register(int priority, T listener)
    {
        throw new NotImplementedException();
    }

    public void unregister(EventToken token)
    {
        throw new NotImplementedException();
    }

    public void broadcast(EventHandler<T> handler)
    {
        throw new NotImplementedException();
    }
}
