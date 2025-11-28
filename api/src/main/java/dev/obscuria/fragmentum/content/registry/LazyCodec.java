package dev.obscuria.fragmentum.content.registry;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class LazyCodec<T> implements Codec<T> {

    public LazyCodec(Supplier<Codec<T>> supplier) {
        throw new NotImplementedException();
    }

    @Override
    public <T1> DataResult<Pair<T, T1>> decode(DynamicOps<T1> ops, T1 input) {
        throw new NotImplementedException();
    }

    @Override
    public <T1> DataResult<T1> encode(T input, DynamicOps<T1> ops, T1 prefix) {
        throw new NotImplementedException();
    }
}
