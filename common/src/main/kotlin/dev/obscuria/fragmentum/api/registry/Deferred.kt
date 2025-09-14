package dev.obscuria.fragmentum.api.registry

import net.minecraft.core.Holder

@Suppress("UNCHECKED_CAST")
open class Deferred<T, V : T>(supplier: () -> Holder<T>) : HolderProvider<T>, ValueProvider<V> {

    override val holder: Holder<T> by lazy { supplier.invoke() }
    override val value: V by lazy { holder.value() as V }
}