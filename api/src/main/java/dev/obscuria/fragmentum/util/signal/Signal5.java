package dev.obscuria.fragmentum.util.signal;

import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("unused")
public final class Signal5<P1, P2, P3, P4, P5> extends Signal<Signal5.Listener<P1, P2, P3, P4, P5>>
{
    public void emit(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5)
    {
        throw new NotImplementedException();
    }

    @FunctionalInterface
    public interface Listener<P1, P2, P3, P4, P5>
    {
        void consume(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    }
}
