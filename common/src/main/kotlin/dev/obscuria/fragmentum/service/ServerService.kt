package dev.obscuria.fragmentum.service

import dev.obscuria.fragmentum.server.FragmentumServerRegistry

interface ServerService {

    fun registerCommand(registrar: FragmentumServerRegistry.CommandRegistrar)
}