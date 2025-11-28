package dev.obscuria.fragmentum.content.util.signal;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.resources.ResourceLocation;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public abstract class Signal<T> {

    private static final ResourceLocation UNBOUND = Fragmentum.key("unbound");
    private final List<Connection<T>> connections = new ArrayList<>();

    public void connect(T listener) {
        connect(null, false, null, listener);
    }

    public void connect(@Nullable Object source, T listener) {
        connect(source, false, null, listener);
    }

    public void connect(boolean oneShot, T listener) {
        connect(null, oneShot, null, listener);
    }

    public void connect(@Nullable Object source, boolean oneShot, T listener) {
        connect(source, oneShot, null, listener);
    }

    public void connect(@Nullable Object source, boolean oneShot, @Nullable Signal0 breaker, T listener) {
        final var connection = new Connection<T>(Objects.requireNonNullElse(source, UNBOUND), oneShot, listener);
        connections.add(connection);
        if (breaker == null) return;
        breaker.connect(true, () -> disconnect(connection));
    }

    public void disconnect(Object source) {
        connections.removeIf(it -> it.source == source);
    }

    protected void emit(Consumer<T> consumer) {
        connections.removeIf(it -> {
            consumer.accept(it.listener);
            return it.oneShot;
        });
    }

    private void disconnect(Connection<T> connection) {
        connections.remove(connection);
    }

    private record Connection<T>(Object source, boolean oneShot, T listener) {}
}
