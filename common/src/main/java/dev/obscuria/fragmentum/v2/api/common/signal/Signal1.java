package dev.obscuria.fragmentum.v2.api.common.signal;

import dev.obscuria.fragmentum.common.signal._Signal1;

@SuppressWarnings("unused")
public interface Signal1<P1> extends Signal<Signal1.Listener<P1>> {

    static <P1> Signal1<P1> create() {
        return new _Signal1<>();
    }

    void emit(P1 p1);

    @FunctionalInterface
    interface Listener<P1> {

        void consume(P1 p1);
    }
}
