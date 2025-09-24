package dev.obscuria.fragmentum.util.signal;

@SuppressWarnings("unused")
public final class Signal3<P1, P2, P3> extends Signal<Signal3.Listener<P1, P2, P3>>
{
    public void emit(P1 p1, P2 p2, P3 p3)
    {
        emit(it -> it.consume(p1, p2, p3));
    }

    @FunctionalInterface
    public interface Listener<P1, P2, P3>
    {
        void consume(P1 p1, P2 p2, P3 p3);
    }
}
