package dev.obscuria.fragmentum.content.registry;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class LazyCodec<T> implements Codec<T> {

    private final Supplier<Codec<T>> supplier;

    public LazyCodec(Supplier<Codec<T>> supplier) {
        this.supplier = Suppliers.memoize(supplier::get);
    }

    @Override
    public <T1> DataResult<Pair<T, T1>> decode(DynamicOps<T1> ops, T1 input) {
        return supplier.get().decode(ops, input);
    }

    @Override
    public <T1> DataResult<T1> encode(T input, DynamicOps<T1> ops, T1 prefix) {
        return supplier.get().encode(input, ops, prefix);
    }
}
