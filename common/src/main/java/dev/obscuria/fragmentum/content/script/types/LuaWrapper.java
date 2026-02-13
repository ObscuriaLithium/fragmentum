package dev.obscuria.fragmentum.content.script.types;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class LuaWrapper<T> extends LuaUserdata {

    @Getter public final T source;
    private final LuaOps<T> ops;

    public LuaWrapper(final T source, final LuaOps<T> ops) {
        super(source);
        this.source = source;
        this.ops = ops;
        final var builder = new Builder();
        this.build(builder);
        builder.build();
    }

    protected abstract void build(Builder builder);

    protected <R> LibFunction method0(LuaOps<R> result, Method0<T, R> method) {
        return new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue self) {
                return result.wrap(method.invoke(ops.unwrap(self)));
            }
        };
    }

    protected <A, R> LibFunction method1(LuaOps<A> argA, LuaOps<R> result, Method1<T, A, R> method) {
        return new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue self, LuaValue a) {
                return result.wrap(method.invoke(ops.unwrap(self), argA.unwrap(a)));
            }
        };
    }

    protected <A, B, R> LibFunction method2(LuaOps<A> argA, LuaOps<B> argB, LuaOps<R> result, Method2<T, A, B, R> method) {
        return new ThreeArgFunction() {
            @Override
            public LuaValue call(LuaValue self, LuaValue a, LuaValue b) {
                return result.wrap(method.invoke(ops.unwrap(self), argA.unwrap(a), argB.unwrap(b)));
            }
        };
    }

    protected <A, B, C, R> LibFunction method3(LuaOps<A> argA, LuaOps<B> argB, LuaOps<C> argC, LuaOps<R> result, Method3<T, A, B, C, R> method) {
        return new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                return result.wrap(method.invoke(ops.unwrap(args.arg(1)), argA.unwrap(args.arg(2)), argB.unwrap(args.arg(3)), argC.unwrap(args.arg(4))));
            }
        };
    }

    protected <R> LibFunction nilMethod0(LuaOps.Nilable<R> result, NilMethod0<T, R> method) {
        return new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue self) {
                return result.wrap(method.invoke(ops.unwrap(self)));
            }
        };
    }

    protected <A, R> LibFunction nilMethod1(LuaOps<A> argA, LuaOps.Nilable<R> result, NilMethod1<T, A, R> method) {
        return new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue self, LuaValue a) {
                return result.wrap(method.invoke(ops.unwrap(self), argA.unwrap(a)));
            }
        };
    }

    protected <A, B, R> LibFunction nilMethod2(LuaOps<A> argA, LuaOps<B> argB, LuaOps.Nilable<R> result, NilMethod2<T, A, B, R> method) {
        return new ThreeArgFunction() {
            @Override
            public LuaValue call(LuaValue self, LuaValue a, LuaValue b) {
                return result.wrap(method.invoke(ops.unwrap(self), argA.unwrap(a), argB.unwrap(b)));
            }
        };
    }

    protected <A, B, C, R> LibFunction nilMethod3(LuaOps<A> argA, LuaOps<B> argB, LuaOps<C> argC, LuaOps.Nilable<R> result, NilMethod3<T, A, B, C, R> method) {
        return new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                return result.wrap(method.invoke(ops.unwrap(args.arg(1)), argA.unwrap(args.arg(2)), argB.unwrap(args.arg(3)), argC.unwrap(args.arg(4))));
            }
        };
    }

    protected LibFunction voidMethod0(VoidMethod0<T> method) {
        return new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue self) {
                method.invoke(ops.unwrap(self));
                return NIL;
            }
        };
    }

    protected <A> LibFunction voidMethod1(LuaOps<A> argA, VoidMethod1<T, A> method) {
        return new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue self, LuaValue a) {
                method.invoke(ops.unwrap(self), argA.unwrap(a));
                return NIL;
            }
        };
    }

    protected <A, B> LibFunction voidMethod2(LuaOps<A> argA, LuaOps<B> argB, VoidMethod2<T, A, B> method) {
        return new ThreeArgFunction() {
            @Override
            public LuaValue call(LuaValue self, LuaValue a, LuaValue b) {
                method.invoke(ops.unwrap(self), argA.unwrap(a), argB.unwrap(b));
                return NIL;
            }
        };
    }

    protected <A, B, C> LibFunction voidMethod3(LuaOps<A> argA, LuaOps<B> argB, LuaOps<C> argC, VoidMethod3<T, A, B, C> method) {
        return new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                method.invoke(ops.unwrap(args.arg(1)), argA.unwrap(args.arg(2)), argB.unwrap(args.arg(3)), argC.unwrap(args.arg(4)));
                return NIL;
            }
        };
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

        private final Map<String, Property<T, ?>> properties = new HashMap<>();
        private final Map<String, LibFunction> methods = new HashMap<>();

        public <V> void put(String name, Property<T, V> property) {
            this.properties.put(name, property);
        }

        public void put(String name, LibFunction method) {
            this.methods.put(name, method);
        }

        private void build() {

            final var meta = new LuaTable();

            meta.set("__index", new TwoArgFunction() {

                @Override
                public LuaValue call(LuaValue userdata, LuaValue key) {
                    final var name = key.checkjstring();
                    final var method = methods.get(name);
                    if (method != null) return method;
                    final var property = properties.get(name);
                    if (property != null) return property.get(source);
                    return NIL;
                }
            });

            meta.set("__newindex", new ThreeArgFunction() {

                @Override
                public LuaValue call(LuaValue userdata, LuaValue key, LuaValue value) {
                    final var name = key.checkjstring();
                    final var property = properties.get(name);
                    if (property != null) property.set(LuaWrapper.this.source, value);
                    return NIL;
                }
            });

            setmetatable(meta);
        }
    }

    public record Property<T, V>(
            LuaOps<V> ops,
            @Nullable Function<T, V> getter,
            @Nullable BiConsumer<T, V> setter) {

        public static <T, V> Property<T, V> of(LuaOps<V> ops, Function<T, V> getter, BiConsumer<T, V> setter) {
            return new Property<>(ops, getter, setter);
        }

        public static <T, V> Property<T, V> readOnly(LuaOps<V> ops, Function<T, V> getter) {
            return new Property<>(ops, getter, null);
        }

        public static <T, V> Property<T, V> writeOnly(LuaOps<V> ops, BiConsumer<T, V> setter) {
            return new Property<>(ops, null, setter);
        }

        public LuaValue get(T source) {
            if (getter == null) return NIL;
            return ops.wrap(getter.apply(source));
        }

        public void set(T source, LuaValue luaValue) {
            if (setter == null) return;
            setter.accept(source, ops.unwrap(luaValue));
        }
    }
}
