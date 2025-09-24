package dev.obscuria.fragmentum.extension;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Function;

@SuppressWarnings("unused")
public interface CodecExtension
{
    static <T> Codec<T> withAlternative(Codec<T> codec, Codec<? extends T> alternative)
    {
        throw new NotImplementedException();
    }

    static <T> Codec<T> validate(Codec<T> codec, Function<T, DataResult<T>> validator)
    {
        throw new NotImplementedException();
    }
}
