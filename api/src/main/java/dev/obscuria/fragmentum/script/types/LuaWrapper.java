package dev.obscuria.fragmentum.script.types;

import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.*;

import java.util.function.BiConsumer;
import java.util.function.Function;

@SuppressWarnings("all")
public abstract class LuaWrapper<T> extends LuaUserdata {

    public LuaWrapper(final T source, final LuaOps<T> ops) {
        super(source);
    }

    public T getSource() {
        throw new NotImplementedException();
    }

    protected abstract void build(Builder builder);

    protected <R> LibFunction method0(LuaOps<R> result, Method0<T, R> method) {
        throw new NotImplementedException();
    }

    protected <A, R> LibFunction method1(LuaOps<A> argA, LuaOps<R> result, Method1<T, A, R> method) {
        throw new NotImplementedException();
    }

    protected <A, B, R> LibFunction method2(LuaOps<A> argA, LuaOps<B> argB, LuaOps<R> result, Method2<T, A, B, R> method) {
        throw new NotImplementedException();
    }

    protected <A, B, C, R> LibFunction method3(LuaOps<A> argA, LuaOps<B> argB, LuaOps<C> argC, LuaOps<R> result, Method3<T, A, B, C, R> method) {
        throw new NotImplementedException();
    }

    protected <R> LibFunction nilMethod0(LuaOps.Nilable<R> result, NilMethod0<T, R> method) {
        throw new NotImplementedException();
    }

    protected <A, R> LibFunction nilMethod1(LuaOps<A> argA, LuaOps.Nilable<R> result, NilMethod1<T, A, R> method) {
        throw new NotImplementedException();
    }

    protected <A, B, R> LibFunction nilMethod2(LuaOps<A> argA, LuaOps<B> argB, LuaOps.Nilable<R> result, NilMethod2<T, A, B, R> method) {
        throw new NotImplementedException();
    }

    protected <A, B, C, R> LibFunction nilMethod3(LuaOps<A> argA, LuaOps<B> argB, LuaOps<C> argC, LuaOps.Nilable<R> result, NilMethod3<T, A, B, C, R> method) {
        throw new NotImplementedException();
    }

    protected LibFunction voidMethod0(VoidMethod0<T> method) {
        throw new NotImplementedException();
    }

    protected <A> LibFunction voidMethod1(LuaOps<A> argA, VoidMethod1<T, A> method) {
        throw new NotImplementedException();
    }

    protected <A, B> LibFunction voidMethod2(LuaOps<A> argA, LuaOps<B> argB, VoidMethod2<T, A, B> method) {
        throw new NotImplementedException();
    }

    protected <A, B, C> LibFunction voidMethod3(LuaOps<A> argA, LuaOps<B> argB, LuaOps<C> argC, VoidMethod3<T, A, B, C> method) {
        throw new NotImplementedException();
    }

    @FunctionalInterface
    public interface Method0<S, R> {

        R invoke(S self);
    }

    @FunctionalInterface
    public interface Method1<S, A, R> {

        R invoke(S self, A a);
    }

    @FunctionalInterface
    public interface Method2<S, A, B, R> {

        R invoke(S self, A a, B b);
    }

    @FunctionalInterface
    public interface Method3<S, A, B, C, R> {

        R invoke(S self, A a, B b, C c);
    }

    @FunctionalInterface
    public interface NilMethod0<S, R> {

        @Nullable R invoke(S self);
    }

    @FunctionalInterface
    public interface NilMethod1<S, A, R> {

        @Nullable R invoke(S self, A a);
    }

    @FunctionalInterface
    public interface NilMethod2<S, A, B, R> {

        @Nullable R invoke(S self, A a, B b);
    }

    @FunctionalInterface
    public interface NilMethod3<S, A, B, C, R> {

        @Nullable R invoke(S self, A a, B b, C c);
    }

    @FunctionalInterface
    public interface VoidMethod0<S> {

        void invoke(S self);
    }

    @FunctionalInterface
    public interface VoidMethod1<S, A> {

        void invoke(S self, A a);
    }

    @FunctionalInterface
    public interface VoidMethod2<S, A, B> {

        void invoke(S self, A a, B b);
    }

    @FunctionalInterface
    public interface VoidMethod3<S, A, B, C> {

        void invoke(S self, A a, B b, C c);
    }

    @FunctionalInterface
    public interface QuadConsumer<A, B, C, D> {

        void accept(A a, B b, C c, D d);
    }

    public class Builder {

        public <V> void put(String name, Property<T, V> property) {
            throw new NotImplementedException();
        }

        public void put(String name, LibFunction method) {
            throw new NotImplementedException();
        }
    }

    public record Property<T, V>(
            LuaOps<V> ops,
            @Nullable Function<T, V> getter,
            @Nullable BiConsumer<T, V> setter) {

        public static <T, V> Property<T, V> of(LuaOps<V> ops, Function<T, V> getter, BiConsumer<T, V> setter) {
            throw new NotImplementedException();
        }

        public static <T, V> Property<T, V> readOnly(LuaOps<V> ops, Function<T, V> getter) {
            throw new NotImplementedException();
        }

        public static <T, V> Property<T, V> writeOnly(LuaOps<V> ops, BiConsumer<T, V> setter) {
            throw new NotImplementedException();
        }

        public LuaValue get(T source) {
            throw new NotImplementedException();
        }

        public void set(T source, LuaValue luaValue) {
            throw new NotImplementedException();
        }
    }
}
