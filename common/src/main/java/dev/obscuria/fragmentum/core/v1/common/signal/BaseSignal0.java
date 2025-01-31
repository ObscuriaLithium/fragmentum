package dev.obscuria.fragmentum.core.v1.common.signal;

import dev.obscuria.fragmentum.api.v1.common.signal.Signal0;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class BaseSignal0 extends BaseSignal<Signal0.Listener> implements Signal0
{
    public void emit()
    {
        this.emit(Listener::consume);
    }
}