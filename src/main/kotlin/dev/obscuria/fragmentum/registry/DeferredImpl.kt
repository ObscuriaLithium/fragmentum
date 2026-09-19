package dev.obscuria.fragmentum.registry

import com.google.common.base.Suppliers
import dev.obscuria.fragmentum.api.registry.Deferred
import net.minecraft.core.Holder
import org.jetbrains.annotations.ApiStatus
import java.util.function.Supplier

@ApiStatus.Internal
internal class DeferredImpl<T>(supplier: () -> Holder<T>) : Deferred<T> {

	private val holder: Supplier<Holder<T>> = Suppliers.memoize<Holder<T>>(supplier::invoke)

	override fun isBound(): Boolean {
		return holder().isBound
	}

	override fun holder(): Holder<T> {
		return holder.get()
	}

	override fun <V> `as`(): Deferred<V> {
		return Deferred.create {
			@Suppress("UNCHECKED_CAST")
			holder.get() as Holder<V>
		}
	}

	override fun get(): T {
		return holder().value()
	}
}
