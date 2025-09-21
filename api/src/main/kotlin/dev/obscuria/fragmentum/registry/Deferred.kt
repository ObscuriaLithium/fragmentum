@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.registry

import net.minecraft.core.Holder

open class Deferred<T, V : T>(supplier: () -> Holder<T>) : HolderProvider<T>, ValueProvider<V> {

    override val holder: Holder<T> = error("API stub")
    override val value: V = error("API stub")
}