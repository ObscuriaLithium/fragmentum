package dev.obscuria.fragmentum.api.server

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.commands.CommandBuildContext
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands

object FragmentumServerRegistry {

	fun registerCommand(registrar: CommandRegistrar) {
		//? fabric
		//dev.obscuria.fragmentum.platform.fabric.FabricPlatform.registerCommand(registrar)
		//? neoforge
		//dev.obscuria.fragmentum.platform.neoforge.NeoforgePlatform.registerCommand(registrar)
		//? forge
		dev.obscuria.fragmentum.platform.forge.ForgePlatform.registerCommand(registrar)
	}

	fun interface CommandRegistrar {
		fun register(
			dispatcher: CommandDispatcher<CommandSourceStack>,
			context: CommandBuildContext,
			selection: Commands.CommandSelection
		)
	}
}
