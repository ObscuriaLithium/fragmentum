package dev.obscuria.fragmentum.script.wrappers;

import lombok.Getter;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.*;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("all")
public abstract class LuaWrapper<T> extends LuaUserdata {

    @Getter public final T source;

    public LuaWrapper(final T source) {
        super(source);
        this.source = source;
    }

    protected abstract void build(Builder builder);

    protected LibFunction voidMethod0(Consumer<T> func) {
        throw new NotImplementedException();
    }

    protected <A> LibFunction voidMethod1(LuaOps<A> argA, BiConsumer<T, A> func) {
        throw new NotImplementedException();
    }

    protected <A, B> LibFunction voidMethod2(LuaOps<A> argA, LuaOps<B> argB, TriConsumer<T, A, B> func) {
        throw new NotImplementedException();
    }

    protected <A, B, C> LibFunction voidMethod3(LuaOps<A> argA, LuaOps<B> argB, LuaOps<C> argC, QuadConsumer<T, A, B, C> func) {
        throw new NotImplementedException();
    }

    protected <R> LibFunction method0(LuaOps<R> result, Function<T, R> func) {
        throw new NotImplementedException();
    }

    protected <A, R> LibFunction method1(LuaOps<A> argA, LuaOps<R> result, BiFunction<T, A, R> func) {
        throw new NotImplementedException();
    }

    protected <A, B, R> LibFunction method2(LuaOps<A> argA, LuaOps<B> argB, LuaOps<R> result, TriFunction<T, A, B, R> func) {
        throw new NotImplementedException();
    }

    protected <A, B, C, R> LibFunction method3(LuaOps<A> argA, LuaOps<B> argB, LuaOps<C> argC, LuaOps<R> result, QuadFunction<T, A, B, C, R> func) {
        throw new NotImplementedException();
    }

    @FunctionalInterface
    public interface TriFunction<A, B, C, R> {

        R apply(A a, B b, C c);
    }

    @FunctionalInterface
    public interface QuadFunction<A, B, C, D, R> {

        R apply(A a, B b, C c, D d);
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

        public void set(T source, LuaValue value) {
            throw new NotImplementedException();
        }
    }
}
