package dev.obscuria.fragmentum.core.v1.common.signal;

import dev.obscuria.fragmentum.api.v1.common.signal.Signal1;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class BaseSignal1<P1> extends BaseSignal<Signal1.Listener<P1>> implements Signal1<P1>
{
    public void emit(P1 p1)
    {
        this.emit(listener -> listener.consume(p1));
    }
}