package dev.obscuria.fragmentum.content.script.types;

import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

@SuppressWarnings("unused")
public interface LuaOps<T> {

    LuaOps<Integer> INT = nonnil(CoerceJavaToLua::coerce, LuaValue::toint);
    LuaOps<Float> FLOAT = nonnil(CoerceJavaToLua::coerce, LuaValue::tofloat);
    LuaOps<Double> DOUBLE = nonnil(CoerceJavaToLua::coerce, LuaValue::todouble);
    LuaOps<Boolean> BOOL = nonnil(CoerceJavaToLua::coerce, LuaValue::toboolean);
    LuaOps<String> STRING = nonnil(CoerceJavaToLua::coerce, LuaValue::toString);

    LuaValue wrap(T value);

    T unwrap(LuaValue value);

    static <T> LuaOps<T> nonnil(Wrapper<T> wrapper, Unwrapper<T> unwrapper) {
        throw new NotImplementedException();
    }

    static <T> Nilable<T> nilable(NilableWrapper<T> wrapper, NilableUnwrapper<T> unwrapper) {
        throw new NotImplementedException();
    }

    interface Nilable<T> extends LuaOps<T> {

        @Override LuaValue wrap(@Nullable T value);

        @Override @Nullable T unwrap(LuaValue value);
    }

    @FunctionalInterface
    interface Wrapper<T> {

        LuaValue wrap(T value);
    }

    @FunctionalInterface
    interface Unwrapper<T> {

        T unwrap(LuaValue value);
    }

    @FunctionalInterface
    interface NilableWrapper<T> {

        LuaValue wrap(@Nullable T value);
    }

    @FunctionalInterface
    interface NilableUnwrapper<T> {

        @Nullable T unwrap(LuaValue value);
    }
}