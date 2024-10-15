package dev.obscuria.fragmentum.api.v1.common.event;

import dev.obscuria.fragmentum.api.v1.common.V1Common;

public interface Event<T>
{
    static <T> Event<T> create()
    {
        return V1Common.INSTANCE.newEvent();
    }

    EventToken register(T listener);

    EventToken register(int priority, T listener);

    void unregister(EventToken token);

    void broadcast(EventHandler<T> handler);
}
