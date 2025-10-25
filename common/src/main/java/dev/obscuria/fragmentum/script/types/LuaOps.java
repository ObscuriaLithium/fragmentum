package dev.obscuria.fragmentum.script.types;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import java.util.function.Function;

public interface LuaOps<T> {

    LuaOps<Integer> INT = new Instance<>(CoerceJavaToLua::coerce, LuaValue::toint);
    LuaOps<Float> FLOAT = new Instance<>(CoerceJavaToLua::coerce, LuaValue::tofloat);
    LuaOps<Double> DOUBLE = new Instance<>(CoerceJavaToLua::coerce, LuaValue::todouble);
    LuaOps<Boolean> BOOL = new Instance<>(CoerceJavaToLua::coerce, LuaValue::toboolean);
    LuaOps<String> STRING = new Instance<>(CoerceJavaToLua::coerce, LuaValue::toString);

    LuaValue wrap(T value);

    T unwrap(LuaValue value);

    record Instance<T>(
            Function<T, LuaValue> wrapper,
            Function<LuaValue, T> unwrapper
    ) implements LuaOps<T> {

        @Override
        public LuaValue wrap(T value) {
            return wrapper.apply(value);
        }

        @Override
        public T unwrap(LuaValue value) {
            return unwrapper.apply(value);
        }
    }
}