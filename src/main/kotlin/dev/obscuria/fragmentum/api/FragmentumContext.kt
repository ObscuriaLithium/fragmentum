package dev.obscuria.fragmentum.api

import dev.obscuria.fragmentum.FragmentumContextImpl
import net.minecraft.core.RegistryAccess
import net.minecraft.server.MinecraftServer
import net.minecraft.world.level.Level
import java.util.*

object FragmentumContext {

	fun server(): MinecraftServer {
		return FragmentumContextImpl.server()
	}

	fun serverOrNull(): MinecraftServer? {
		return FragmentumContextImpl.serverOrNull()
	}

	fun serverOptional(): Optional<MinecraftServer> {
		return FragmentumContextImpl.serverOptional()
	}

	fun isOnServerThread(): Boolean {
		return FragmentumContextImpl.isOnServerThread()
	}

	fun registryAccessOf(level: Level): RegistryAccess {
		return FragmentumContextImpl.registryAccessOf(level)
	}

	fun registryAccess(): RegistryAccess {
		return FragmentumContextImpl.registryAccess()
	}
}
