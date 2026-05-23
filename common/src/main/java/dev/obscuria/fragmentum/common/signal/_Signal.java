package dev.obscuria.fragmentum.common.signal;

import dev.obscuria.fragmentum.v2.api.common.signal.Signal;
import dev.obscuria.fragmentum.v2.api.common.signal.Signal0;
import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class _Signal<T> implements Signal<T> {

    private static final Identifier UNBOUND = Fragmentum.id("unbound");
    private final List<Connection<T>> connections = new ArrayList<>();

    @Override public void connect(@Nullable Object source, boolean oneShot, @Nullable Signal0 breaker, T listener) {
        final var connection = new Connection<T>(Objects.requireNonNullElse(source, UNBOUND), oneShot, listener);
        connections.add(connection);
        if (breaker == null) return;
        breaker.connect(true, () -> disconnect(connection));
    }

    @Override public void disconnect(Object source) {
        connections.removeIf(it -> it.source == source);
    }

    protected void emitInternal(Consumer<T> consumer) {
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
