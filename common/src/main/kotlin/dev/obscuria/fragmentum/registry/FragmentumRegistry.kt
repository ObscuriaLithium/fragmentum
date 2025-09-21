@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.registry

import dev.obscuria.fragmentum.Fragmentum

object FragmentumRegistry {

    fun registrar(modId: String): Registrar {
        return Fragmentum.SERVICES.registrar(modId)
    }
}