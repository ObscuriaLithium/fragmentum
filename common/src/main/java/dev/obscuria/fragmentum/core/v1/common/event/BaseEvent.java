package dev.obscuria.fragmentum.core.v1.common.event;

import com.google.common.collect.Lists;
import dev.obscuria.fragmentum.api.v1.common.event.Event;
import dev.obscuria.fragmentum.api.v1.common.event.EventHandler;
import dev.obscuria.fragmentum.api.v1.common.event.EventToken;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.UUID;

@ApiStatus.Internal
public final class BaseEvent<T> implements Event<T>
{
    private final List<Registration<T>> registrations = Lists.newArrayList();
    private boolean dirty;

    @Override
    public EventToken register(T listener)
    {
        return register(1000, listener);
    }

    @Override
    public EventToken register(int priority, T listener)
    {
        final var token = new BaseEventToken(UUID.randomUUID(), this);
        this.registrations.add(new Registration<>(token, priority, listener));
        this.dirty = true;
        return token;
    }

    @Override
    public void unregister(EventToken token)
    {
        this.registrations.removeIf(registration -> registration.token.equals(token));
    }

    @Override
    public void broadcast(EventHandler<T> handler)
    {
        this.sortIfDirty();
        this.registrations.stream()
                .map(Registration::listener)
                .forEach(handler::handle);
    }

    private void sortIfDirty()
    {
        if (!dirty) return;
        this.dirty = false;
        this.registrations.sort(Registration::compareTo);
    }

    private record Registration<T>(EventToken token,
                                   int priority,
                                   T listener) implements Comparable<Registration<T>>
    {
        @Override
        public int compareTo(Registration<T> other)
        {
            return Integer.compare(this.priority, other.priority);
        }
    }
}
