package dev.obscuria.fragmentum.api.registry

import dev.obscuria.fragmentum.api.Fragmentum

object FragmentumRegistry {

    fun registrar(modId: String): Registrar = Fragmentum.SERVICES.registrar(modId)
}