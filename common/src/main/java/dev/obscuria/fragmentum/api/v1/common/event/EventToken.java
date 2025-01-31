package dev.obscuria.fragmentum.api.v1.common.event;

/**
 * A token representing the registration of a listener to an event.
 * The `EventToken` interface provides a mechanism to manage listener registrations
 * by allowing the associated listener to be unregistered from the event when needed.
 */
@SuppressWarnings("unused")
public interface EventToken
{
    /**
     * Unregisters the listener associated with this token.
     * <p>
     * This method removes the listener from the event, ensuring it will no longer
     * receive notifications when the event is broadcast.
     */
    void unregister();
}