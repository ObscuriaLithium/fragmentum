package dev.obscuria.fragmentum.platform.fabric

//? fabric {
/*import dev.obscuria.fragmentum.Platform
import dev.obscuria.fragmentum.api.server.FragmentumServerRegistry
import net.fabricmc.api.EnvType
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.loader.api.FabricLoader
import org.jetbrains.annotations.ApiStatus
import java.nio.file.Path

@ApiStatus.Internal
internal object FabricPlatform : Platform {

	fun registerCommand(registrar: FragmentumServerRegistry.CommandRegistrar) {
		CommandRegistrationCallback.EVENT.register(registrar::register)
	}

	override fun isModLoaded(modId: String): Boolean {
		return FabricLoader.getInstance().isModLoaded(modId)
	}

	override fun loader(): Platform.ModLoader {
		return Platform.ModLoader.FABRIC
	}

	override fun isDevelopmentEnvironment(): Boolean {
		return FabricLoader.getInstance().isDevelopmentEnvironment
	}

	override fun isClient(): Boolean {
		return FabricLoader.getInstance().environmentType == EnvType.CLIENT
	}

	override fun isDedicatedServer(): Boolean {
		return FabricLoader.getInstance().environmentType == EnvType.SERVER
	}

	override fun configDir(): Path {
		return FabricLoader.getInstance().configDir
	}
}
*///?}
