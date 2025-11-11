package dev.obscuria.fragmentum.util.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public final class Event<T>
{
    private final List<Registration<T>> registrations = new ArrayList<>();
    private boolean dirty = false;

    public EventToken register(T listener)
    {
        return register(1000, listener);
    }

    public EventToken register(int priority, T listener)
    {
        final var token = new Token(UUID.randomUUID(), this);
        registrations.add(new Registration<>(token, priority, listener));
        dirty = true;
        return token;
    }

    public void unregister(EventToken token)
    {
        registrations.removeIf(it -> it.token == token);
    }

    public void broadcast(EventHandler<T> handler)
    {
        this.sortIfDirty();
        registrations.forEach(it -> handler.handle(it.listener));
    }

    private void sortIfDirty()
    {
        if (!dirty) return;
        Collections.sort(registrations);
        this.dirty = false;
    }

    private record Registration<T>(EventToken token, int priority, T listener) implements Comparable<Registration<T>>
    {
        @Override
        public int compareTo(Registration<T> other)
        {
            return Integer.compare(priority, other.priority);
        }
    }

    private record Token(UUID uuid, Event<?> event) implements EventToken
    {
        @Override
        public void unregister()
        {
            event.unregister(this);
        }
    }
}
