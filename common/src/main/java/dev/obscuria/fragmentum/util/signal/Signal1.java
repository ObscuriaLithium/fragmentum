package dev.obscuria.fragmentum.util.signal;

@SuppressWarnings("unused")
public final class Signal1<P1> extends Signal<Signal1.Listener<P1>>
{
    public void emit(P1 p1)
    {
        emit(it -> it.consume(p1));
    }

    @FunctionalInterface
    public interface Listener<P1>
    {
        void consume(P1 p1);
    }
}
