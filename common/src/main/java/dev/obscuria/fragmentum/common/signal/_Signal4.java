package dev.obscuria.fragmentum.common.signal;

import dev.obscuria.fragmentum.v2.api.common.signal.Signal4;

public final class _Signal4<P1, P2, P3, P4> extends _Signal<Signal4.Listener<P1, P2, P3, P4>> implements Signal4<P1, P2, P3, P4> {

    @Override public void emit(P1 p1, P2 p2, P3 p3, P4 p4) {
        emitInternal(it -> it.consume(p1, p2, p3, p4));
    }
}
