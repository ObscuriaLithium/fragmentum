package dev.obscuria.fragmentum.fabric.service

import dev.obscuria.fragmentum.api.server.FragmentumServerRegistry
import dev.obscuria.fragmentum.api.service.ServerService
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback

internal class SubFabricServer : ServerService
{
    override fun registerCommand(registrar: FragmentumServerRegistry.CommandRegistrar)
    {
        CommandRegistrationCallback.EVENT.register(registrar::register)
    }

    companion object
    {
        val INSTANCE = SubFabricServer()
    }
}