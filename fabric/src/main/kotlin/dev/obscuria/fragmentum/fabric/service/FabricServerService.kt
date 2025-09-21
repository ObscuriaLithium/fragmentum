package dev.obscuria.fragmentum.fabric.service

import dev.obscuria.fragmentum.server.FragmentumServerRegistry
import dev.obscuria.fragmentum.service.ServerService
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback

internal object FabricServerService : ServerService {

    override fun registerCommand(registrar: FragmentumServerRegistry.CommandRegistrar) {
        CommandRegistrationCallback.EVENT.register(registrar::register)
    }
}