package dev.obscuria.fragmentum.v2.api.common.signal;

import dev.obscuria.fragmentum.common.signal._Signal2;

@SuppressWarnings("unused")
public interface Signal3<P1, P2, P3> extends Signal<Signal3.Listener<P1, P2, P3>> {

    static <P1, P2, P3> Signal2<P1, P2> create() {
        return new _Signal2<>();
    }

    void emit(P1 p1, P2 p2, P3 p3);

    @FunctionalInterface
    interface Listener<P1, P2, P3> {

        void consume(P1 p1, P2 p2, P3 p3);
    }
}
