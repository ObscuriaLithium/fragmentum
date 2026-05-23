package dev.obscuria.fragmentum.common.signal;

import dev.obscuria.fragmentum.v2.api.common.signal.Signal0;

public final class _Signal0 extends _Signal<Signal0.Listener> implements Signal0 {

    @Override public void emit() {
        emitInternal(Listener::consume);
    }
}
