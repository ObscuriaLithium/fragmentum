package dev.obscuria.fragmentum.api.service

import dev.obscuria.fragmentum.api.server.FragmentumServerRegistry

interface ServerService {

    fun registerCommand(registrar: FragmentumServerRegistry.CommandRegistrar)
}