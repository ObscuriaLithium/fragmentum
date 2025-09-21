@file:Suppress("unused", "UNCHECKED_CAST", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.extension

import com.mojang.datafixers.util.Either
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import java.util.function.Function

object MinecraftExtension {

    fun <T> Codec<T>.withAlternative(alternative: Codec<out T>): Codec<T> {
        val alt = alternative as Codec<T>
        return Codec.either(this, alt).xmap(
            { it.map(Function.identity(), Function.identity()) },
            { Either.left(it) })
    }

    fun <T> Codec<T>.validate(checker: (T) -> DataResult<T>): Codec<T> {
        return this.flatXmap(checker, checker)
    }
}