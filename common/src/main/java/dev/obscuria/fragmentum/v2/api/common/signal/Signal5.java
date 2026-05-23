package dev.obscuria.fragmentum.v2.api.common.signal;

import dev.obscuria.fragmentum.common.signal._Signal5;

@SuppressWarnings("unused")
public interface Signal5<P1, P2, P3, P4, P5> extends Signal<Signal5.Listener<P1, P2, P3, P4, P5>> {

    static <P1, P2, P3, P4, P5> Signal5<P1, P2, P3, P4, P5> create() {
        return new _Signal5<>();
    }

    void emit(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);

    @FunctionalInterface
    interface Listener<P1, P2, P3, P4, P5> {

        void consume(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    }
}
