package dev.obscuria.fragmentum.api.v1.common.signal;

/**
 * A specialized signal interface with three parameters.
 * The `Signal3` interface represents a signal that passes three arguments to its listeners
 * when triggered. This allows listeners to respond to events while consuming the provided data.
 *
 * @param <P1> The type of the first parameter passed to the signal's listeners.
 * @param <P2> The type of the second parameter passed to the signal's listeners.
 * @param <P3> The type of the third parameter passed to the signal's listeners.
 */
@SuppressWarnings("unused")
public interface Signal3<P1, P2, P3> extends Signal<Signal3.Listener<P1, P2, P3>>
{
    /**
     * Emits the signal, notifying all connected listeners with the provided arguments.
     *
     * @param p1 The first argument to pass to the listeners when the signal is emitted.
     * @param p2 The second argument to pass to the listeners when the signal is emitted.
     * @param p3 The third argument to pass to the listeners when the signal is emitted.
     */
    void emit(P1 p1, P2 p2, P3 p3);

    /**
     * A functional interface representing a listener for a `Signal3`.
     * <p>
     * The listener is invoked when the signal is emitted and consumes three input parameters.
     *
     * @param <P1> The type of the first parameter consumed by the listener.
     * @param <P2> The type of the second parameter consumed by the listener.
     * @param <P3> The type of the third parameter consumed by the listener.
     */
    @FunctionalInterface
    interface Listener<P1, P2, P3>
    {
        /**
         * The method to execute when the signal is emitted.
         *
         * @param p1 The first parameter passed to the listener when the signal is emitted.
         * @param p2 The second parameter passed to the listener when the signal is emitted.
         * @param p3 The third parameter passed to the listener when the signal is emitted.
         */
        void consume(P1 p1, P2 p2, P3 p3);
    }
}