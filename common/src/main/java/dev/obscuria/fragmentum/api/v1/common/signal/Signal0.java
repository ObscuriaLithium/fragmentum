package dev.obscuria.fragmentum.api.v1.common.signal;

/**
 * A specialized signal interface with no parameters.
 * The `Signal0` interface represents a signal that does not pass any arguments to its listeners
 * when triggered. It provides an easy way to emit simple events and allows listeners to respond
 * to those events without requiring input data.
 */
@SuppressWarnings("unused")
public interface Signal0 extends Signal<Signal0.Listener>
{
    /**
     * Emits the signal, notifying all connected listeners.
     * <p>
     * When this method is called, all listeners connected to the signal are executed in the order
     * they were added.
     */
    void emit();

    /**
     * A functional interface representing a listener for a `Signal0`.
     * <p>
     * The listener is invoked when the signal is emitted and does not consume any input arguments.
     */
    @FunctionalInterface
    interface Listener
    {
        /**
         * The method to execute when the signal is emitted.
         */
        void consume();
    }
}