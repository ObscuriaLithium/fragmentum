package dev.obscuria.fragmentum.content.util.signal;

import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("unused")
public final class Signal0 extends Signal<Signal0.Listener>
{
    public void emit()
    {
        throw new NotImplementedException();
    }

    @FunctionalInterface
    public interface Listener
    {
        void consume();
    }
}
