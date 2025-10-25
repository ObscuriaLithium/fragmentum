package dev.obscuria.fragmentum.script.types;

import lombok.Getter;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class LuaWrapper<T> extends LuaUserdata {

    @Getter public final T source;

    public LuaWrapper(final T source) {
        super(source);
        this.source = source;
        final var builder = new Builder();
        this.build(builder);
        builder.build();
    }

    protected abstract void build(Builder builder);

    protected LibFunction voidMethod0(Consumer<T> func) {
        return new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue self) {
                func.accept(source);
                return NIL;
            }
        };
    }

    protected <A> LibFunction voidMethod1(LuaOps<A> argA, BiConsumer<T, A> func) {
        return new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue self, LuaValue a) {
                func.accept(source, argA.unwrap(a));
                return NIL;
            }
        };
    }

    protected <A, B> LibFunction voidMethod2(LuaOps<A> argA, LuaOps<B> argB, TriConsumer<T, A, B> func) {
        return new ThreeArgFunction() {
            @Override
            public LuaValue call(LuaValue self, LuaValue a, LuaValue b) {
                func.accept(source, argA.unwrap(a), argB.unwrap(b));
                return NIL;
            }
        };
    }

    protected <A, B, C> LibFunction voidMethod3(LuaOps<A> argA, LuaOps<B> argB, LuaOps<C> argC, QuadConsumer<T, A, B, C> func) {
        return new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                func.accept(source, argA.unwrap(args.arg(2)), argB.unwrap(args.arg(3)), argC.unwrap(args.arg(4)));
                return NIL;
            }
        };
    }

    protected <R> LibFunction method0(LuaOps<R> result, Function<T, R> func) {
        return new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue self) {
                return result.wrap(func.apply(source));
            }
        };
    }

    protected <A, R> LibFunction method1(LuaOps<A> argA, LuaOps<R> result, BiFunction<T, A, R> func) {
        return new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue self, LuaValue a) {
                return result.wrap(func.apply(source, argA.unwrap(a)));
            }
        };
    }

    protected <A, B, R> LibFunction method2(LuaOps<A> argA, LuaOps<B> argB, LuaOps<R> result, TriFunction<T, A, B, R> func) {
        return new ThreeArgFunction() {
            @Override
            public LuaValue call(LuaValue self, LuaValue a, LuaValue b) {
                return result.wrap(func.apply(source, argA.unwrap(a), argB.unwrap(b)));
            }
        };
    }

    protected <A, B, C, R> LibFunction method3(LuaOps<A> argA, LuaOps<B> argB, LuaOps<C> argC, LuaOps<R> result, QuadFunction<T, A, B, C, R> func) {
        return new VarArgFunction() {
            @Override
            public Varargs invoke(Varargs args) {
                return result.wrap(func.apply(source, argA.unwrap(args.arg(2)), argB.unwrap(args.arg(3)), argC.unwrap(args.arg(4))));
            }
        };
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
