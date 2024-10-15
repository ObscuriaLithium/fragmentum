package dev.obscuria.fragmentum.core.v1.common.event;

import dev.obscuria.fragmentum.api.v1.common.event.Event;
import dev.obscuria.fragmentum.api.v1.common.event.EventToken;

import java.util.UUID;

public record EventTokenImpl(UUID uuid,
                             Event<?> event) implements EventToken
{
    @Override
    public void unregister()
    {
        event.unregister(this);
    }
}
