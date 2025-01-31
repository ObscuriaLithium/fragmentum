package dev.obscuria.fragmentum.api.v1.common.signal;

/**
 * A specialized signal interface with one parameter.
 * The `Signal1` interface represents a signal that passes a single argument to its listeners
 * when triggered. This allows listeners to respond to the event while consuming the provided data.
 *
 * @param <P1> The type of the parameter passed to the signal's listeners.
 */
@SuppressWarnings("unused")
public interface Signal1<P1> extends Signal<Signal1.Listener<P1>>
{
    /**
     * Emits the signal, notifying all connected listeners with the provided argument.
     *
     * @param p1 The argument to pass to the listeners when the signal is emitted.
     */
    void emit(P1 p1);

    /**
     * A functional interface representing a listener for a `Signal1`.
     * <p>
     * The listener is invoked when the signal is emitted and consumes a single input parameter.
     *
     * @param <P1> The type of the parameter consumed by the listener.
     */
    @FunctionalInterface
    interface Listener<P1>
    {
        /**
         * The method to execute when the signal is emitted.
         *
         * @param p1 The parameter passed to the listener when the signal is emitted.
         */
        void consume(P1 p1);
    }
}