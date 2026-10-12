package dev.obscuria.fragmentum.registry

import dev.obscuria.fragmentum.api.registry.Deferred
import net.minecraft.core.Holder
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class DeferredImpl<T>(supplier: () -> Holder<T>) : Deferred<T> {

	private val holder by lazy(supplier)

	override val value: T get() = holder.value()

	override fun isBound(): Boolean = holder.isBound

	override fun isInitialized(): Boolean = holder.isBound

	override fun holder(): Holder<T> = holder

	override fun get(): T = value

	@Suppress("UNCHECKED_CAST")
	override fun <V> `as`(): Deferred<V> {
		return Deferred.create { holder as Holder<V> }
	}
}
