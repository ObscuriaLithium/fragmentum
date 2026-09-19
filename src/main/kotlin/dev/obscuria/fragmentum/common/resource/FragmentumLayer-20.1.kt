package dev.obscuria.fragmentum.common.resource

//? 1.20.1 {
import dev.obscuria.fragmentum.Fragmentum
import net.minecraft.SharedConstants
import net.minecraft.network.chat.Component
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.PathPackResources
import net.minecraft.server.packs.repository.Pack
import net.minecraft.server.packs.repository.PackSource
import net.minecraft.server.packs.repository.RepositorySource
import net.minecraft.server.packs.resources.IoSupplier
import net.minecraft.world.flag.FeatureFlagSet
import org.jetbrains.annotations.ApiStatus
import java.io.InputStream
import java.nio.file.Path
import java.util.function.Consumer

@ApiStatus.Internal
internal object FragmentumLayer {

	val CLIENT_METADATA: Pack.Info
	val SERVER_METADATA: Pack.Info

	fun createSource(type: PackType): Source {
		val userDir = Path.of(System.getProperty("user.dir")).toAbsolutePath().normalize()
		return Source(userDir.resolve("config/${Fragmentum.MOD_ID}"), type)
	}

	init {
		val clientVersion = SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES)
		val serverVersion = SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA)
		CLIENT_METADATA = Pack.Info(Component.literal("Global resources"), clientVersion, FeatureFlagSet.of())
		SERVER_METADATA = Pack.Info(Component.literal("Global configurations"), serverVersion, FeatureFlagSet.of())
	}

	data class Source(val directory: Path, val type: PackType) : RepositorySource {

		override fun loadPacks(consumer: Consumer<Pack>) {
			val metadata = if (type == PackType.CLIENT_RESOURCES) CLIENT_METADATA else SERVER_METADATA
			consumer.accept(
				Pack.create(
					"generated/fragmentum_layer",
					Component.literal("Fragmentum Layer"),
					true,
					{ Resources(it, directory) },
					metadata,
					type,
					Pack.Position.TOP,
					false,
					PackSource.BUILT_IN
				)
			)
		}
	}

	class Resources(packId: String, root: Path) : PathPackResources(packId, root, true) {

		override fun getRootResource(vararg path: String): IoSupplier<InputStream>? {
			val fileName = path.joinToString("/")
			if (fileName != "pack.png") return super.getRootResource(*path)
			return IoSupplier {
				runCatching {
					val resource = Fragmentum::class.java.getResourceAsStream("/assets/icon.png")
					checkNotNull(resource) { "Resource `assets/icon.png` not found" }
					return@runCatching resource
				}.getOrElse {
					Fragmentum.LOGGER.error("Failed to load FragmentumLayer icon", it)
					return@getOrElse InputStream.nullInputStream()
				}
			}
		}
	}
}
//?}
