package dev.obscuria.fragmentum.api.common

import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import java.util.function.Function

object CodexExtensions {

	fun <T> Codec<T>.withAlternative(alt: Codec<out T>): Codec<T> {
		//? 1.20.1 {
		return Codec.either(this, alt).xmap(
			{ it.map(Function.identity(), Function.identity()) },
			{ com.mojang.datafixers.util.Either.left(it) })
		//?} else
		//return Codec.withAlternative(this, alt)
	}

	fun <T> Codec<T>.withAlternatives(alt1: Codec<out T>, alt2: Codec<out T>): Codec<T> {
		return this.withAlternative(alt1).withAlternative(alt2)
	}

	fun <T> Codec<T>.validate(checker: Function<T, DataResult<T>>): Codec<T> {
		//? 1.20.1
		return this.flatXmap(checker, checker)
		//? >1.20.1
		//return this.validate(checker)
	}
}
