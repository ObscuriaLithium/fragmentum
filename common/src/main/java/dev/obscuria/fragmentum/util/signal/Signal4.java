package dev.obscuria.fragmentum.util.signal;

@SuppressWarnings("unused")
public final class Signal4<P1, P2, P3, P4> extends Signal<Signal4.Listener<P1, P2, P3, P4>>
{
    public void emit(P1 p1, P2 p2, P3 p3, P4 p4)
    {
        emit(it -> it.consume(p1, p2, p3, p4));
    }

    @FunctionalInterface
    public interface Listener<P1, P2, P3, P4>
    {
        void consume(P1 p1, P2 p2, P3 p3, P4 p4);
    }
}
