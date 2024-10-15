package dev.obscuria.fragmentum.core.v1.common.signal;

import dev.obscuria.fragmentum.api.v1.common.signal.Signal2;

public final class Signal2Impl<P1, P2> extends SignalImpl<Signal2.Listener<P1, P2>> implements Signal2<P1, P2>
{
    public void emit(P1 p1, P2 p2)
    {
        this.emit(listener -> listener.consume(p1, p2));
    }
}