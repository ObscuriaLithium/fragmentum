package dev.obscuria.fragmentum.platform.fabric

//? fabric {
/*import com.mojang.serialization.Codec
import dev.obscuria.fragmentum.api.registry.Deferred
import dev.obscuria.fragmentum.api.registry.Registrar
import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.fabricmc.fabric.api.event.registry.RegistryAttribute
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.level.levelgen.Heightmap
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal data class FabricRegistrar(val modId: String) : Registrar {

	override fun <T, V : T> register(
		registry: Registry<T>,
		id: ResourceLocation,
		supplier: () -> V
	): Deferred<T> where T : Any {
		val holder = Registry.registerForHolder(registry, id, supplier.invoke())
		return Deferred.create { holder }
	}

	@Suppress("UNCHECKED_CAST")
	override fun <T, V : T> register(
		registryKey: ResourceKey<out Registry<T>>,
		id: ResourceLocation,
		supplier: () -> V
	): Deferred<T> where T : Any {
		val registry = BuiltInRegistries.REGISTRY.get(registryKey.location())
		checkNotNull(registry) { "No registry found for id $id" }
		return register(registry as Registry<T>, id, supplier)
	}

	override fun <T> createRegistry(
		registryKey: ResourceKey<Registry<T>>
	): Registry<T> where T : Any {
		return FabricRegistryBuilder.createSimple<T>(registryKey)
			.attribute(RegistryAttribute.SYNCED)
			.buildAndRegister()
	}

	override fun <T> createDataRegistry(
		registryKey: ResourceKey<Registry<T>>,
		codec: () -> Codec<T>
	) {
		DynamicRegistries.register(registryKey, codec.invoke())
	}

	override fun <T> createSyncedDataRegistry(
		registryKey: ResourceKey<Registry<T>>,
		codec: () -> Codec<T>
	) {
		DynamicRegistries.registerSynced(registryKey, codec.invoke())
	}

	override fun <T> createSyncedDataRegistry(
		registryKey: ResourceKey<Registry<T>>,
		codec: () -> Codec<T>,
		networkCodec: () -> Codec<T>
	) {
		DynamicRegistries.registerSynced(registryKey, codec.invoke(), networkCodec.invoke())
	}

	override fun <T : LivingEntity> registerAttributes(
		type: Deferred<EntityType<T>>,
		builder: AttributeSupplier.Builder
	) {
		FabricDefaultAttributeRegistry.register(type.get(), builder)
	}

	override fun <T : LivingEntity> registerAttributes(
		type: Deferred<EntityType<T>>,
		builder: () -> AttributeSupplier.Builder
	) {
		FabricDefaultAttributeRegistry.register(type.get(), builder.invoke())
	}

	//~ if >1.20.1 'SpawnPlacements.Type' -> 'SpawnPlacementType' {
	override fun <T : Mob> registerSpawnPlacement(
		type: Deferred<EntityType<T>>,
		placementType: SpawnPlacementType,
		heightmap: Heightmap.Types,
		predicate: SpawnPlacements.SpawnPredicate<T>
	) {
		SpawnPlacements.register(type.get(), placementType, heightmap, predicate)
	}
	//~}
}
*///?}
