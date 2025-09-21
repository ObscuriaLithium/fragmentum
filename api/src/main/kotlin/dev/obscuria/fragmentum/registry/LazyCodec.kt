@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.registry

import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.DynamicOps

class LazyCodec<T>(supplier: () -> Codec<T>) : Codec<T> {

    override fun <S> decode(ops: DynamicOps<S>, input: S): DataResult<Pair<T, S>> = error("API stub")

    override fun <S> encode(input: T, ops: DynamicOps<S>, prefix: S): DataResult<S> = error("API stub")
}