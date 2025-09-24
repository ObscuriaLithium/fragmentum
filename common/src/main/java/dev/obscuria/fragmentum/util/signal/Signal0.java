package dev.obscuria.fragmentum.util.signal;

@SuppressWarnings("unused")
public final class Signal0 extends Signal<Signal0.Listener>
{
    public void emit()
    {
        emit(Listener::consume);
    }

    @FunctionalInterface
    public interface Listener
    {
        void consume();
    }
}
