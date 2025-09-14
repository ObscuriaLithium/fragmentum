package dev.obscuria.fragmentum.api.server

import com.mojang.brigadier.CommandDispatcher
import dev.obscuria.fragmentum.api.Fragmentum
import net.minecraft.commands.CommandBuildContext
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands

object FragmentumServerRegistry {

    fun registerCommand(registrar: CommandRegistrar) = Fragmentum.SERVICES.server().registerCommand(registrar)

    fun interface CommandRegistrar {
        fun register(
            dispatcher: CommandDispatcher<CommandSourceStack>,
            registryAccess: CommandBuildContext,
            environment: Commands.CommandSelection
        )
    }
}