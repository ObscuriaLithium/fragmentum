package dev.obscuria.fragmentum.api.v1.common.signal;

public interface Signal1<P1> extends Signal<Signal1.Listener<P1>> {

    void emit(P1 p1);

    @FunctionalInterface
    interface Listener<P1> {

        void consume(P1 p1);
    }
}