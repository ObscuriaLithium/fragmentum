package dev.obscuria.fragmentum.common.signal;

import dev.obscuria.fragmentum.v2.api.common.signal.Signal1;

public final class _Signal1<P1> extends _Signal<Signal1.Listener<P1>> implements Signal1<P1> {

    @Override public void emit(P1 p1) {
        emitInternal(it -> it.consume(p1));
    }
}
