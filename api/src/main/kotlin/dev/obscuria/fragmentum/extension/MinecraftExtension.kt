@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType", "UnusedReceiverParameter")

package dev.obscuria.fragmentum.extension

import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult

object MinecraftExtension {

    fun <T> Codec<T>.withAlternative(alternative: Codec<out T>): Codec<T> = error("API stub")

    fun <T> Codec<T>.validate(checker: (T) -> DataResult<T>): Codec<T> = error("API stub")
}