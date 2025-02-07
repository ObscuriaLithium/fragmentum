package dev.obscuria.fragmentum.api.registry;

import com.google.common.base.Suppliers
import net.minecraft.core.Holder
import java.util.function.Supplier

@Suppress("UNCHECKED_CAST")
open class Deferred<T, V : T>(supplier: Supplier<Holder<T>>) : Supplier<V>
{
    private val source: Supplier<Holder<T>> = Suppliers.memoize(supplier::get)

    fun holder(): Holder<T> = source.get()

    fun value(): V = holder().value() as V

    override fun get(): V = value()
}
