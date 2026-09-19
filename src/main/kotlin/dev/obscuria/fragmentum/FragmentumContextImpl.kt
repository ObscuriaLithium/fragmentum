package dev.obscuria.fragmentum

import net.minecraft.client.Minecraft
import net.minecraft.core.RegistryAccess
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.Level
import org.jetbrains.annotations.ApiStatus
import java.util.Optional
import java.util.concurrent.atomic.AtomicReference

@ApiStatus.Internal
internal object FragmentumContextImpl {

	private val currentServer = AtomicReference<MinecraftServer?>(null)

	fun notifyServerStarting(server: MinecraftServer) {
		currentServer.set(server)
	}

	fun notifyServerStopped(server: MinecraftServer) {
		currentServer.compareAndSet(server, null)
	}

	fun server(): MinecraftServer {
		return currentServer.get() ?: error("No active MinecraftServer in this context")
	}

	fun serverOrNull(): MinecraftServer? {
		return currentServer.get()
	}

	fun serverOptional(): Optional<MinecraftServer> {
		return Optional.ofNullable(currentServer.get())
	}

	fun isOnServerThread(): Boolean {
		return currentServer.get()?.isSameThread == true
	}

	fun registryAccessOf(level: Level): RegistryAccess {
		if (level is ServerLevel) return level.server.registryAccess()
		return clientRegistryAccess()
	}

	fun registryAccess(): RegistryAccess {
		val server = currentServer.get()
		if (server != null) {
			if (server.isSameThread) return server.registryAccess()
			if (!Fragmentum.PLATFORM.isClient()) return server.registryAccess()
		} else if (!Fragmentum.PLATFORM.isClient()) {
			error("Can't resolve RegistryAccess: no server present and not a client environment")
		}
		return clientRegistryAccess()
	}

	private fun clientRegistryAccess(): RegistryAccess {
		val minecraft = Minecraft.getInstance()
		return minecraft.singleplayerServer?.registryAccess()
			?: minecraft.connection?.registryAccess()
			?: error("Can't resolve client RegistryAccess: no world/connection loaded")
	}
}
