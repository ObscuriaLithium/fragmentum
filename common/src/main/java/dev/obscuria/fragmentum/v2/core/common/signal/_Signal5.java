package dev.obscuria.fragmentum.v2.core.common.signal;

import dev.obscuria.fragmentum.v2.api.common.signal.Signal5;

public final class _Signal5<P1, P2, P3, P4, P5> extends _Signal<Signal5.Listener<P1, P2, P3, P4, P5>> implements Signal5<P1, P2, P3, P4, P5> {

    @Override public void emit(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
        emitInternal(it -> it.consume(p1, p2, p3, p4, p5));
    }
}
