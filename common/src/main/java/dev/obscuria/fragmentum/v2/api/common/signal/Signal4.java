package dev.obscuria.fragmentum.v2.api.common.signal;

import dev.obscuria.fragmentum.common.signal._Signal4;

@SuppressWarnings("unused")
public interface Signal4<P1, P2, P3, P4> extends Signal<Signal4.Listener<P1, P2, P3, P4>> {

    static <P1, P2, P3, P4> Signal4<P1, P2, P3, P4> create() {
        return new _Signal4<>();
    }

    void emit(P1 p1, P2 p2, P3 p3, P4 p4);

    @FunctionalInterface
    interface Listener<P1, P2, P3, P4> {

        void consume(P1 p1, P2 p2, P3 p3, P4 p4);
    }
}
