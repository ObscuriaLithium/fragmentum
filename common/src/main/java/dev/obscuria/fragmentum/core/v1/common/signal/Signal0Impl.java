package dev.obscuria.fragmentum.core.v1.common.signal;

import dev.obscuria.fragmentum.api.v1.common.signal.Signal0;

public final class Signal0Impl extends SignalImpl<Signal0.Listener> implements Signal0
{
    public void emit()
    {
        this.emit(Listener::consume);
    }
}