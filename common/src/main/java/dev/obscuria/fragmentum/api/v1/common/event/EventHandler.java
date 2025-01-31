package dev.obscuria.fragmentum.api.v1.common.event;

/**
 * A functional interface representing the handler for an event.
 * The `EventHandler` interface defines a single method that specifies how an event
 * should be processed for a given listener. It is typically used when broadcasting
 * an event to all registered listeners.
 *
 * @param <T> The type of listener associated with the event.
 */
@FunctionalInterface
public interface EventHandler<T>
{
    /**
     * Handles the event for the specified listener.
     *
     * @param listener The listener to which the event should be applied.
     */
    void handle(T listener);
}