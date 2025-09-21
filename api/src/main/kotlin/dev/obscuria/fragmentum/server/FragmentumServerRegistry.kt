@file:Suppress("unused", "UNUSED_PARAMETER", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.server

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.commands.CommandBuildContext
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands

object FragmentumServerRegistry {

    fun registerCommand(registrar: CommandRegistrar): Unit = error("API stub")

    fun interface CommandRegistrar {

        fun register(
            dispatcher: CommandDispatcher<CommandSourceStack>,
            registryAccess: CommandBuildContext,
            environment: Commands.CommandSelection
        )
    }
}