package dev.obscuria.fragmentum.v2.api.common.signal;

import dev.obscuria.fragmentum.v2.core.common.signal._Signal2;

@SuppressWarnings("unused")
public interface Signal2<P1, P2> extends Signal<Signal2.Listener<P1, P2>> {

    static <P1, P2> Signal2<P1, P2> create() {
        return new _Signal2<>();
    }

    void emit(P1 p1, P2 p2);

    @FunctionalInterface
    interface Listener<P1, P2> {

        void consume(P1 p1, P2 p2);
    }
}
