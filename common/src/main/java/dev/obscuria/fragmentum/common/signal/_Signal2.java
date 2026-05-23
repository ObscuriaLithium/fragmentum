package dev.obscuria.fragmentum.common.signal;

import dev.obscuria.fragmentum.v2.api.common.signal.Signal2;

public final class _Signal2<P1, P2> extends _Signal<Signal2.Listener<P1, P2>> implements Signal2<P1, P2> {

    @Override public void emit(P1 p1, P2 p2) {
        emitInternal(it -> it.consume(p1, p2));
    }
}
