package dev.obscuria.fragmentum.platform.forge

//? forge {
import com.mojang.serialization.Codec
import com.mojang.serialization.Lifecycle
import dev.obscuria.fragmentum.api.registry.Deferred
import dev.obscuria.fragmentum.api.registry.Registrar
import dev.obscuria.fragmentum.mixin.BuiltInRegistriesAccessor
import net.minecraft.core.Holder
import net.minecraft.core.MappedRegistry
import net.minecraft.core.Registry
import net.minecraft.core.WritableRegistry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.SpawnPlacements
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent
import net.minecraftforge.registries.DataPackRegistryEvent
import net.minecraftforge.registries.DeferredRegister
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal data class ForgeRegistrar(val modId: String) : Registrar {

	override fun <T, V : T> register(
		registry: Registry<T>,
		id: ResourceLocation,
		supplier: () -> V
	): Deferred<T> where T : Any {
		return register(registry.key(), id, supplier)
	}

	@Suppress("UNCHECKED_CAST")
	override fun <T, V : T> register(
		registryKey: ResourceKey<out Registry<T>>,
		id: ResourceLocation,
		supplier: () -> V
	): Deferred<T> where T : Any {
		val deferredRegister = DeferredRegister.create(registryKey, id.namespace)
		ForgeEntrypoint.register(modId, deferredRegister)
		val registryObject = deferredRegister.register(id.path, supplier)
		return Deferred.create { registryObject.getHolder().orElseThrow() as Holder<T> }
	}

	@Suppress("UNCHECKED_CAST", "DEPRECATION")
	override fun <T> createRegistry(
		registryKey: ResourceKey<Registry<T>>
	): Registry<T> where T : Any {
		val registry = MappedRegistry(registryKey, Lifecycle.stable(), false)
		val root = BuiltInRegistriesAccessor.`fragmentum$getWritableRegistry`() as WritableRegistry<Registry<*>>

		(root as MappedRegistry<*>).unfreeze()
		try {
			root.register(registryKey as ResourceKey<Registry<*>>, registry, Lifecycle.stable())
		} finally {
			(root as MappedRegistry<*>).freeze()
		}
		return registry
	}

	override fun <T> createDataRegistry(
		registryKey: ResourceKey<Registry<T>>,
		codec: () -> Codec<T>
	) {
		ForgeEntrypoint.addListener<DataPackRegistryEvent.NewRegistry>(modId) {
			it.dataPackRegistry(registryKey, codec.invoke())
		}
	}

	override fun <T> createSyncedDataRegistry(
		registryKey: ResourceKey<Registry<T>>,
		codec: () -> Codec<T>
	) {
		ForgeEntrypoint.addListener<DataPackRegistryEvent.NewRegistry>(modId) {
			it.dataPackRegistry<T>(registryKey, codec.invoke(), codec.invoke())
		}
	}

	override fun <T> createSyncedDataRegistry(
		registryKey: ResourceKey<Registry<T>>,
		codec: () -> Codec<T>,
		networkCodec: () -> Codec<T>
	) {
		ForgeEntrypoint.addListener<DataPackRegistryEvent.NewRegistry>(modId) {
			it.dataPackRegistry(registryKey, codec.invoke(), networkCodec.invoke())
		}
	}

	override fun <T : LivingEntity> registerAttributes(
		type: Deferred<EntityType<T>>,
		builder: AttributeSupplier.Builder
	) {
		ForgeEntrypoint.addListener<EntityAttributeCreationEvent>(modId) {
			it.put(type.get(), builder.build())
		}
	}

	override fun <T : LivingEntity> registerAttributes(
		type: Deferred<EntityType<T>>,
		builder: () -> AttributeSupplier.Builder
	) {
		ForgeEntrypoint.addListener<EntityAttributeCreationEvent>(modId) {
			it.put(type.get(), builder.invoke().build())
		}
	}

	override fun <T : Mob> registerSpawnPlacement(
		type: Deferred<EntityType<T>>,
		placementType: SpawnPlacements.Type,
		heightmap: Heightmap.Types,
		predicate: SpawnPlacements.SpawnPredicate<T>
	) {
		ForgeEntrypoint.addListener<SpawnPlacementRegisterEvent>(modId) {
			it.register(
				type.get(), placementType, heightmap, predicate,
				SpawnPlacementRegisterEvent.Operation.REPLACE
			)
		}
	}
}
//?}
