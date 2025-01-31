package dev.obscuria.fragmentum.api.v1.common.signal;

import dev.obscuria.fragmentum.api.v1.common.V1Common;

import javax.annotation.Nullable;

/**
 * A generic event system interface for managing signals and listeners.
 * The `Signal` interface provides a flexible way to handle event-driven systems by allowing
 * listeners to connect to signals and react to events. Signals can support multiple parameters,
 * ensuring extensibility for different types of event handling scenarios.
 *
 * @param <T> The type of listener associated with the signal.
 */
@SuppressWarnings("unused")
public interface Signal<T>
{
    /**
     * Creates a new signal with no parameters.
     *
     * @return A {@link Signal0} instance that represents a signal with no parameters.
     */
    static Signal0 newSignal0()
    {
        return V1Common.INSTANCE.newSignal0();
    }

    /**
     * Creates a new signal with one parameter.
     *
     * @param <P1> The type of the first parameter.
     * @return A {@link Signal1} instance that represents a signal with one parameter.
     */
    static <P1> Signal1<P1> newSignal1()
    {
        return V1Common.INSTANCE.newSignal1();
    }

    /**
     * Creates a new signal with two parameters.
     *
     * @param <P1> The type of the first parameter.
     * @param <P2> The type of the second parameter.
     * @return A {@link Signal2} instance that represents a signal with two parameters.
     */
    static <P1, P2> Signal2<P1, P2> newSignal2()
    {
        return V1Common.INSTANCE.newSignal2();
    }

    /**
     * Creates a new signal with three parameters.
     *
     * @param <P1> The type of the first parameter.
     * @param <P2> The type of the second parameter.
     * @param <P3> The type of the third parameter.
     * @return A {@link Signal3} instance that represents a signal with three parameters.
     */
    static <P1, P2, P3> Signal3<P1, P2, P3> newSignal3()
    {
        return V1Common.INSTANCE.newSignal3();
    }

    /**
     * Connects a listener to this signal using a key and additional options.
     * <p>
     * The listener will be executed when the signal is triggered. If `oneShot` is set to `true`,
     * the listener will automatically disconnect after being called once. An optional breaker
     * signal can also be provided, which will disconnect the listener when the breaker signal is triggered.
     *
     * @param key      An object used as a unique identifier for the connection.
     * @param listener The listener to connect to the signal.
     * @param oneShot  Whether the listener should disconnect after being executed once.
     * @param breaker  A {@link Signal0} acting as a breaker signal to disconnect the listener if triggered. May be null.
     */
    void connect(Object key, T listener, boolean oneShot, @Nullable Signal0 breaker);

    /**
     * Connects a listener to this signal using a key with a one-shot option.
     *
     * @param key      An object used as a unique identifier for the connection.
     * @param listener The listener to connect to the signal.
     * @param oneShot  Whether the listener should disconnect after being executed once.
     */
    void connect(Object key, T listener, boolean oneShot);

    /**
     * Connects a listener to this signal using a key and a breaker signal.
     *
     * @param key      An object used as a unique identifier for the connection.
     * @param listener The listener to connect to the signal.
     * @param breaker  A {@link Signal0} acting as a breaker signal to disconnect the listener if triggered. May be null.
     */
    void connect(Object key, T listener, @Nullable Signal0 breaker);

    /**
     * Connects a listener to this signal using a key.
     *
     * @param key      An object used as a unique identifier for the connection.
     * @param listener The listener to connect to the signal.
     */
    void connect(Object key, T listener);

    /**
     * Connects a listener to this signal with additional options.
     *
     * @param listener The listener to connect to the signal.
     * @param oneShot  Whether the listener should disconnect after being executed once.
     * @param breaker  A {@link Signal0} acting as a breaker signal to disconnect the listener if triggered. May be null.
     */
    void connect(T listener, boolean oneShot, @Nullable Signal0 breaker);

    /**
     * Connects a listener to this signal with a one-shot option.
     *
     * @param listener The listener to connect to the signal.
     * @param oneShot  Whether the listener should disconnect after being executed once.
     */
    void connect(T listener, boolean oneShot);

    /**
     * Connects a listener to this signal with a breaker signal.
     *
     * @param listener The listener to connect to the signal.
     * @param breaker  A {@link Signal0} acting as a breaker signal to disconnect the listener if triggered. May be null.
     */
    void connect(T listener, @Nullable Signal0 breaker);

    /**
     * Connects a listener to this signal.
     *
     * @param listener The listener to connect to the signal.
     */
    void connect(T listener);

    /**
     * Disconnects all listeners associated with a specific key.
     *
     * @param key The object used as the unique identifier for the connection to remove.
     */
    void disconnect(Object key);
}