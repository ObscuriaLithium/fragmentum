package dev.obscuria.fragmentum.common.resource

import dev.obscuria.fragmentum.Fragmentum
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.PathPackResources
import net.minecraft.server.packs.repository.Pack
import net.minecraft.server.packs.repository.RepositorySource
import org.jetbrains.annotations.ApiStatus
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.Paths
import java.util.function.Consumer

@ApiStatus.Internal
internal data class BuiltInRepositorySource(
	val type: PackType
) : RepositorySource {

	override fun loadPacks(consumer: Consumer<Pack>) {
		BuiltInPackRegistry.forEachRegistration(type) { modId, reg ->
			val rootPath = resolveRootPath(reg.modClass, modId, reg.directory) ?: return@forEachRegistration
			val packId = "${modId}/${reg.directory}"

			//? 1.20.1 {
			Pack.readMetaAndCreate(
				packId, reg.displayName, reg.config.required,
				{ PathPackResources(packId, rootPath, true) }, type,
				reg.config.defaultPosition, reg.source
			)?.let(consumer::accept)
			//?} else {
			/*Pack.readMetaAndCreate(
				net.minecraft.server.packs.PackLocationInfo(
					"$modId/${reg.directory}",
					reg.displayName,
					reg.source,
					java.util.Optional.empty()
				),
				PathPackResources.PathResourcesSupplier(rootPath),
				type, reg.config.asVanilla()
			)?.let(consumer::accept)
			*///?}
		}
	}

	private fun resolveRootPath(modClass: Class<*>, modId: String, directory: String): Path? {
		return runCatching {
			val resource = modClass.getResource("/$directory") ?: error("Resource not found: $directory")
			val uri = resource.toURI()
			when (uri.scheme) {
				"jar" -> FileSystems.getFileSystem(uri).getPath("/$directory")
				"file", "union" -> Paths.get(uri)
				else -> error("Unsupported URI scheme: ${uri.scheme}")
			}
		}.getOrElse {
			Fragmentum.LOGGER.error("Failed to resolve `{}:{}`", modId, directory, it)
			return null
		}
	}
}
