package dev.obscuria.fragmentum.v2.api.common.signal;

import dev.obscuria.fragmentum.v2.core.common.signal._Signal3;

@SuppressWarnings("unused")
public interface Signal3<P1, P2, P3> extends Signal<Signal3.Listener<P1, P2, P3>> {

    static <P1, P2, P3> Signal3<P1, P2, P3> create() {
        return new _Signal3<>();
    }

    void emit(P1 p1, P2 p2, P3 p3);

    @FunctionalInterface
    interface Listener<P1, P2, P3> {

        void consume(P1 p1, P2 p2, P3 p3);
    }
}
