package dev.obscuria.fragmentum.v2.core.common.signal;

import dev.obscuria.fragmentum.v2.api.common.signal.Signal3;

public final class _Signal3<P1, P2, P3> extends _Signal<Signal3.Listener<P1, P2, P3>> implements Signal3<P1, P2, P3> {

    @Override public void emit(P1 p1, P2 p2, P3 p3) {
        emitInternal(it -> it.consume(p1, p2, p3));
    }
}
