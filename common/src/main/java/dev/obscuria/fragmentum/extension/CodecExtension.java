package dev.obscuria.fragmentum.extension;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

import java.util.function.Function;

@SuppressWarnings("unused")
public interface CodecExtension
{
    static <T> Codec<T> withAlternative(Codec<T> codec, Codec<? extends T> alt)
    {
        return Codec.either(codec, alt).xmap(it -> it.map(Function.identity(), Function.identity()), Either::left);
    }

    static <T> Codec<T> withAlternative(Codec<T> codec, Codec<? extends T> alt1, Codec<? extends T> alt2)
    {
        return withAlternative(withAlternative(codec, alt1), alt2);
    }

    static <T> Codec<T> validate(Codec<T> codec, Function<T, DataResult<T>> validator)
    {
        return codec.flatXmap(validator, validator);
    }
}
