package dev.obscuria.fragmentum.v2.api.common.signal;

import dev.obscuria.fragmentum.v2.core.common.signal._Signal0;

@SuppressWarnings("unused")
public interface Signal0 extends Signal<Signal0.Listener> {

    static Signal0 create() {
        return new _Signal0();
    }

    void emit();

    @FunctionalInterface
    interface Listener {

        void consume();
    }
}
