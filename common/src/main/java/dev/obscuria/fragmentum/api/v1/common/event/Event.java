package dev.obscuria.fragmentum.api.v1.common.event;

import dev.obscuria.fragmentum.api.v1.common.V1Common;

/**
 * A generic event system interface for registering listeners and broadcasting events.
 * The `Event` interface provides a framework for event handling by supporting listener
 * registration, sorting by priority, and broadcasting to all registered listeners.
 *
 * @param <T> The type of listener associated with the event.
 */
public interface Event<T>
{
    /**
     * Creates a new instance of an event.
     *
     * @param <T> The type of listener associated with the event.
     * @return A new instance of {@code Event<T>}.
     */
    static <T> Event<T> create()
    {
        return V1Common.INSTANCE.newEvent();
    }

    /**
     * Registers a listener to the event.
     *
     * @param listener The listener to register.
     * @return An {@link EventToken} representing the registration, which can be used to unregister the listener.
     */
    EventToken register(T listener);

    /**
     * Registers a listener to the event with a specific priority.
     *
     * @param priority The priority of the listener. Higher values indicate higher priority.
     * @param listener The listener to register.
     * @return An {@link EventToken} representing the registration, which can be used to unregister the listener.
     */
    EventToken register(int priority, T listener);

    /**
     * Unregisters a listener associated with the given token.
     *
     * @param token The {@link EventToken} received when the listener was registered.
     *              This token is used to identify the listener to be removed.
     */
    void unregister(EventToken token);

    /**
     * Broadcasts the event to all registered listeners.
     * The handler is used to execute the behavior for each listener. Listeners are invoked in
     * the order of their priority, with higher-priority listeners executed first.
     *
     * @param handler The {@link EventHandler} defining how the event should be handled for each listener.
     */
    void broadcast(EventHandler<T> handler);
}