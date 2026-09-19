package dev.obscuria.fragmentum.common.resource

//? >=1.21.1 {
/*import dev.obscuria.fragmentum.Fragmentum
import net.minecraft.network.chat.Component
import net.minecraft.server.packs.PackLocationInfo
import net.minecraft.server.packs.PackSelectionConfig
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.PathPackResources
import net.minecraft.server.packs.repository.Pack
import net.minecraft.server.packs.repository.PackCompatibility
import net.minecraft.server.packs.repository.PackSource
import net.minecraft.server.packs.repository.RepositorySource
import net.minecraft.server.packs.resources.IoSupplier
import net.minecraft.world.flag.FeatureFlagSet
import org.jetbrains.annotations.ApiStatus
import java.io.InputStream
import java.nio.file.Path
import java.util.*
import java.util.function.Consumer

@ApiStatus.Internal
@Suppress("DEPRECATION")
internal object FragmentumLayer {

	val CONFIG = PackSelectionConfig(true, Pack.Position.TOP, false)
	val INFO = PackLocationInfo(
		"generated/fragmentum_layer",
		Component.literal("Fragmentum Layer"),
		PackSource.BUILT_IN,
		Optional.empty()
	)
	val CLIENT_METADATA = Pack.Metadata(
		Component.literal("Global resources"),
		PackCompatibility.COMPATIBLE,
		FeatureFlagSet.of(),
		mutableListOf()
	)
	val SERVER_METADATA = Pack.Metadata(
		Component.literal("Global configurations"),
		PackCompatibility.COMPATIBLE,
		FeatureFlagSet.of(),
		mutableListOf()
	)

	fun createSource(type: PackType): Source {
		val userDir = Path.of(System.getProperty("user.dir")).toAbsolutePath().normalize()
		return Source(userDir.resolve("config/${Fragmentum.MOD_ID}"), type)
	}

	data class Source(val directory: Path, val type: PackType) : RepositorySource {

		override fun loadPacks(consumer: Consumer<Pack>) {
			val metadata = if (type == PackType.CLIENT_RESOURCES) CLIENT_METADATA else SERVER_METADATA
			val resourceSupplier = ResourcesSupplier(directory)
			consumer.accept(Pack(INFO, resourceSupplier, metadata, CONFIG))
		}
	}

	class Resources(info: PackLocationInfo, root: Path) : PathPackResources(info, root) {

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

	data class ResourcesSupplier(val root: Path) : Pack.ResourcesSupplier {

		override fun openPrimary(info: PackLocationInfo) = Resources(info, root)

		override fun openFull(info: PackLocationInfo, metadata: Pack.Metadata) = Resources(info, root)
	}
}
*///?}
