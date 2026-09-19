package dev.obscuria.fragmentum.platform.forge

//? forge {
import dev.obscuria.fragmentum.Platform
import dev.obscuria.fragmentum.api.server.FragmentumServerRegistry
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.loading.FMLLoader
import net.minecraftforge.fml.loading.FMLPaths
import org.jetbrains.annotations.ApiStatus
import java.nio.file.Path

@ApiStatus.Internal
internal object ForgePlatform : Platform {

	fun registerCommand(registrar: FragmentumServerRegistry.CommandRegistrar) {
		MinecraftForge.EVENT_BUS.addListener<RegisterCommandsEvent> {
			registrar.register(it.dispatcher, it.buildContext, it.commandSelection)
		}
	}

	override fun isModLoaded(modId: String): Boolean {
		return ModList.get().isLoaded(modId)
	}

	override fun isDevelopmentEnvironment(): Boolean {
		return !FMLLoader.isProduction()
	}

	override fun isClient(): Boolean {
		return FMLLoader.getDist().isClient
	}

	override fun isDedicatedServer(): Boolean {
		return FMLLoader.getDist().isDedicatedServer
	}

	override fun configDir(): Path {
		return FMLPaths.CONFIGDIR.get()
	}

	override fun loader(): Platform.ModLoader {
		return Platform.ModLoader.FORGE
	}
}
//?}
