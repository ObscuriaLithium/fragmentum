package dev.obscuria.fragmentum.api.v1.common.signal;

import dev.obscuria.fragmentum.api.v1.common.V1Common;

import javax.annotation.Nullable;

@SuppressWarnings("unused")
public interface Signal<T>
{
    static Signal0 newSignal0()
    {
        return V1Common.INSTANCE.newSignal0();
    }

    static <P1> Signal1<P1> newSignal1()
    {
        return V1Common.INSTANCE.newSignal1();
    }

    static <P1, P2> Signal2<P1, P2> newSignal2()
    {
        return V1Common.INSTANCE.newSignal2();
    }

    static <P1, P2, P3> Signal3<P1, P2, P3> newSignal3()
    {
        return V1Common.INSTANCE.newSignal3();
    }

    void connect(Object key, T listener, boolean oneShot, @Nullable Signal0 breaker);

    void connect(Object key, T listener, boolean oneShot);

    void connect(Object key, T listener, @Nullable Signal0 breaker);

    void connect(Object key, T listener);

    void connect(T listener, boolean oneShot, @Nullable Signal0 breaker);

    void connect(T listener, boolean oneShot);

    void connect(T listener, @Nullable Signal0 breaker);

    void connect(T listener);

    void disconnect(Object key);
}