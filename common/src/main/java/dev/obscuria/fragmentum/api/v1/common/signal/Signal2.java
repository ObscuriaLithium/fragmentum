package dev.obscuria.fragmentum.api.v1.common.signal;

public interface Signal2<P1, P2> extends Signal<Signal2.Listener<P1, P2>> {

    void emit(P1 p2, P2 p1);

    @FunctionalInterface
    interface Listener<P1, P2> {

        void consume(P1 p1, P2 p2);
    }
}