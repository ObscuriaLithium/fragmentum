@file:Suppress("unused", "RedundantNullableReturnType", "UNCHECKED_CAST")

package dev.obscuria.fragmentum.registry

import net.minecraft.core.Holder

open class Deferred<T, V : T>(
    supplier: () -> Holder<T>
) : HolderProvider<T>, ValueProvider<V> {

    override val holder: Holder<T> by lazy { supplier.invoke() }
    override val value: V by lazy { holder.value() as V }
}