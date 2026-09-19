package dev.obscuria.fragmentum.platform.neoforge

//? neoforge {
/*import com.mojang.serialization.Codec
import dev.obscuria.fragmentum.api.registry.Deferred
import dev.obscuria.fragmentum.api.registry.Registrar
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.level.levelgen.Heightmap
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent
import net.neoforged.neoforge.registries.DataPackRegistryEvent
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NewRegistryEvent
import net.neoforged.neoforge.registries.RegistryBuilder
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal data class NeoforgeRegistrar(val modId: String) : Registrar {

	override fun <T, V : T> register(
		registry: Registry<T>,
		id: ResourceLocation,
		supplier: () -> V
	): Deferred<T> where T : Any {
		return register(registry.key(), id, supplier)
	}

	override fun <T, V : T> register(
		registryKey: ResourceKey<out Registry<T>>,
		id: ResourceLocation,
		supplier: () -> V
	): Deferred<T> where T : Any {
		val deferredRegister = DeferredRegister.create(registryKey, id.namespace)
		NeoforgeEntrypoint.register(modId, deferredRegister)
		val deferredHolder = deferredRegister.register(id.path, supplier)
		return Deferred.create { deferredHolder }
	}

	override fun <T> createRegistry(
		registryKey: ResourceKey<Registry<T>>
	): Registry<T> where T : Any {
		val registry = RegistryBuilder(registryKey).create()
		NeoforgeEntrypoint.addListener<NewRegistryEvent>(modId) {
			it.register(registry)
		}
		return registry
	}

	override fun <T> createDataRegistry(
		registryKey: ResourceKey<Registry<T>>,
		codec: () -> Codec<T>
	) {
		NeoforgeEntrypoint.addListener<DataPackRegistryEvent.NewRegistry>(modId) {
			it.dataPackRegistry(registryKey, codec.invoke())
		}
	}

	override fun <T> createSyncedDataRegistry(
		registryKey: ResourceKey<Registry<T>>,
		codec: () -> Codec<T>
	) {
		NeoforgeEntrypoint.addListener<DataPackRegistryEvent.NewRegistry>(modId) {
			it.dataPackRegistry(registryKey, codec.invoke(), codec.invoke())
		}
	}

	override fun <T> createSyncedDataRegistry(
		registryKey: ResourceKey<Registry<T>>,
		codec: () -> Codec<T>,
		networkCodec: () -> Codec<T>
	) {
		NeoforgeEntrypoint.addListener<DataPackRegistryEvent.NewRegistry>(modId) {
			it.dataPackRegistry(registryKey, codec.invoke(), networkCodec.invoke())
		}
	}

	override fun <T : LivingEntity> registerAttributes(
		type: Deferred<EntityType<T>>,
		builder: AttributeSupplier.Builder
	) {
		NeoforgeEntrypoint.addListener<EntityAttributeCreationEvent>(modId) {
			it.put(type.get(), builder.build())
		}
	}

	override fun <T : LivingEntity> registerAttributes(
		type: Deferred<EntityType<T>>,
		builder: () -> AttributeSupplier.Builder
	) {
		NeoforgeEntrypoint.addListener<EntityAttributeCreationEvent>(modId) {
			it.put(type.get(), builder.invoke().build())
		}
	}

	//~ if >1.20.1 'SpawnPlacements.Type' -> 'SpawnPlacementType' {
	override fun <T : Mob> registerSpawnPlacement(
		type: Deferred<EntityType<T>>,
		placementType: SpawnPlacements.Type,
		heightmap: Heightmap.Types,
		predicate: SpawnPlacements.SpawnPredicate<T>
	) {
		NeoforgeEntrypoint.addListener<RegisterSpawnPlacementsEvent>(modId) {
			it.register(
				type.get(), placementType, heightmap, predicate,
				RegisterSpawnPlacementsEvent.Operation.REPLACE
			)
		}
	}
	//~}
}
*///?}
