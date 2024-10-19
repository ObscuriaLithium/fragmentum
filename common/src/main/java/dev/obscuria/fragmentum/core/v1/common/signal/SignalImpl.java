package dev.obscuria.fragmentum.core.v1.common.signal;

import com.google.common.collect.Lists;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal;
import dev.obscuria.fragmentum.api.v1.common.signal.Signal0;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

abstract class SignalImpl<T> implements Signal<T>
{
    private static final ResourceLocation UNBOUND = ResourceLocation.withDefaultNamespace("unbound");
    protected final List<Connection<T>> connections = Lists.newArrayList();

    @Override
    public void connect(Object key, T listener, boolean oneShot, @Nullable Signal0 breaker)
    {
        final var connection = new Connection<>(key, listener, oneShot);
        this.connections.add(connection);
        if (breaker == null) return;
        breaker.connect(() -> disconnect(connection), true);
    }

    @Override
    public void connect(Object key, T listener, boolean oneShot)
    {
        this.connect(key, listener, oneShot, null);
    }

    @Override
    public void connect(Object key, T listener, @Nullable Signal0 breaker)
    {
        this.connect(key, listener, false, breaker);
    }

    @Override
    public void connect(Object key, T listener)
    {
        this.connect(key, listener, false, null);
    }

    @Override
    public void connect(T consumer, boolean oneShot, @Nullable Signal0 recall)
    {
        this.connect(UNBOUND, consumer, oneShot, recall);
    }

    @Override
    public void connect(T consumer, boolean oneShot)
    {
        this.connect(UNBOUND, consumer, oneShot, null);
    }

    @Override
    public void connect(T consumer, @Nullable Signal0 recall)
    {
        this.connect(UNBOUND, consumer, false, recall);
    }

    @Override
    public void connect(T consumer)
    {
        this.connect(UNBOUND, consumer, false, null);
    }

    @Override
    public void disconnect(Object key)
    {
        this.connections.removeIf(connection -> connection.key().equals(key));
    }

    protected void emit(Consumer<T> consumer)
    {
        connections.removeIf(connection -> {
            consumer.accept(connection.listener());
            return connection.oneShot();
        });
    }

    protected void disconnect(Connection<T> connection)
    {
        this.connections.remove(connection);
    }

    protected record Connection<T>(Object key, T listener, boolean oneShot) {}
}