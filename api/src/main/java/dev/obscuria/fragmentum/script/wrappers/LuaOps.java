package dev.obscuria.fragmentum.script.wrappers;

import org.apache.commons.lang3.NotImplementedException;
import org.luaj.vm2.LuaValue;

import java.util.function.Function;

@SuppressWarnings("all")
public interface LuaOps<T> {

    LuaOps<Integer> INT = null;
    LuaOps<Float> FLOAT = null;
    LuaOps<Double> DOUBLE = null;
    LuaOps<Boolean> BOOL = null;

    LuaValue wrap(T value);

    T unwrap(LuaValue value);

    record Instance<T>(
            Function<T, LuaValue> wrapper,
            Function<LuaValue, T> unwrapper
    ) implements LuaOps<T> {

        @Override
        public LuaValue wrap(T value) {
            throw new NotImplementedException();
        }

        @Override
        public T unwrap(LuaValue value) {
            throw new NotImplementedException();
        }
    }
}