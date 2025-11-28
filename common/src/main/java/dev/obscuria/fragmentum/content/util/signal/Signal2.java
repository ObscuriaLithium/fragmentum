package dev.obscuria.fragmentum.content.util.signal;

@SuppressWarnings("unused")
public final class Signal2<P1, P2> extends Signal<Signal2.Listener<P1, P2>> {

    public void emit(P1 p1, P2 p2) {
        emit(it -> it.consume(p1, p2));
    }

    @FunctionalInterface
    public interface Listener<P1, P2> {

        void consume(P1 p1, P2 p2);
    }
}
