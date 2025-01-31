package dev.obscuria.fragmentum.core.v1.common.event;

import dev.obscuria.fragmentum.api.v1.common.event.Event;
import dev.obscuria.fragmentum.api.v1.common.event.EventToken;
import org.jetbrains.annotations.ApiStatus;

import java.util.UUID;

@ApiStatus.Internal
public record BaseEventToken(UUID uuid, Event<?> event) implements EventToken
{
    @Override
    public void unregister()
    {
        event.unregister(this);
    }
}
