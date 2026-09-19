package dev.obscuria.fragmentum.platform.neoforge

//? neoforge {
/*import dev.obscuria.fragmentum.Platform
import dev.obscuria.fragmentum.api.server.FragmentumServerRegistry
import net.neoforged.fml.ModList
import net.neoforged.fml.loading.FMLLoader
import net.neoforged.fml.loading.FMLPaths
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.RegisterCommandsEvent
import org.jetbrains.annotations.ApiStatus
import java.nio.file.Path

@ApiStatus.Internal
internal object NeoforgePlatform : Platform {

	fun registerCommand(registrar: FragmentumServerRegistry.CommandRegistrar) {
		NeoForge.EVENT_BUS.addListener<RegisterCommandsEvent> {
			registrar.register(it.dispatcher, it.buildContext, it.commandSelection)
		}
	}

	override fun isModLoaded(modId: String): Boolean {
		return ModList.get().isLoaded(modId)
	}

	override fun isDevelopmentEnvironment(): Boolean {
		return !FMLLoader /*? if > 1.21.7 {*/ /*.getCurrent()*/ /*?}*/.isProduction()
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
		return Platform.ModLoader.NEOFORGE
	}
}
*///?}
