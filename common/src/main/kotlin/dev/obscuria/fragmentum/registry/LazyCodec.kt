@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.registry

import com.google.common.base.Suppliers
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.DynamicOps
import java.util.function.Supplier

class LazyCodec<T>(supplier: () -> Codec<T>) : Codec<T> {

    private val name: String = supplier.toString()
    private val provider: Supplier<Codec<T>> = Suppliers.memoize { supplier() }

    override fun <S> decode(ops: DynamicOps<S>, input: S): DataResult<Pair<T, S>> {
        return provider.get().decode(ops, input)
    }

    override fun <S> encode(input: T, ops: DynamicOps<S>, prefix: S): DataResult<S> {
        return provider.get().encode(input, ops, prefix)
    }

    override fun toString(): String {
        return "LazyCodec[$name]"
    }
}