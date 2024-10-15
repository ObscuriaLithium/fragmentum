package dev.obscuria.fragmentum.api.v1.common.signal;

public interface Signal0 extends Signal<Signal0.Listener> {

    void emit();

    @FunctionalInterface
    interface Listener {

        void consume();
    }
}