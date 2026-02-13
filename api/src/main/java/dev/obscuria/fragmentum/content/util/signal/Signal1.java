package dev.obscuria.fragmentum.content.util.signal;

import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("unused")
public final class Signal1<P1> extends Signal<Signal1.Listener<P1>>
{
    public void emit(P1 p1)
    {
        throw new NotImplementedException();
    }

    @FunctionalInterface
    public interface Listener<P1>
    {
        void consume(P1 p1);
    }
}
