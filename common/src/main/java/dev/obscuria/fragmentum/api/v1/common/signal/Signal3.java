package dev.obscuria.fragmentum.api.v1.common.signal;

public interface Signal3<P1, P2, P3> extends Signal<Signal3.Listener<P1, P2, P3>> {

    void emit(P1 p1, P2 p2, P3 p3);

    @FunctionalInterface
    interface Listener<P1, P2, P3> {

        void consume(P1 p1, P2 p2, P3 p3);
    }
}