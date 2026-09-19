package dev.obscuria.fragmentum.api.registry

import com.mojang.serialization.Codec
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.level.levelgen.Heightmap

interface Registrar {

	fun <T, V : T> register(
		registry: Registry<T>,
		id: ResourceLocation,
		supplier: () -> V
	): Deferred<T> where T : Any

	fun <T, V : T> register(
		registryKey: ResourceKey<out Registry<T>>,
		id: ResourceLocation,
		supplier: () -> V
	): Deferred<T> where T : Any

	fun <T> createRegistry(
		registryKey: ResourceKey<Registry<T>>
	): Registry<T> where T : Any

	fun <T> createDataRegistry(
		registryKey: ResourceKey<Registry<T>>,
		codec: () -> Codec<T>
	)

	fun <T> createSyncedDataRegistry(
		registryKey: ResourceKey<Registry<T>>,
		codec: () -> Codec<T>
	)

	fun <T> createSyncedDataRegistry(
		registryKey: ResourceKey<Registry<T>>,
		codec: () -> Codec<T>,
		networkCodec: () -> Codec<T>
	)

	fun <T : LivingEntity> registerAttributes(
        type: Deferred<EntityType<T>>,
        builder: AttributeSupplier.Builder
	)

	fun <T : LivingEntity> registerAttributes(
        type: Deferred<EntityType<T>>,
        builder: () -> AttributeSupplier.Builder
	)

	//~ if >1.20.1 'SpawnPlacements.Type' -> 'SpawnPlacementType' {
	fun <T : Mob> registerSpawnPlacement(
        type: Deferred<EntityType<T>>,
        placementType: SpawnPlacements.Type,
        heightmap: Heightmap.Types,
        predicate: SpawnPlacements.SpawnPredicate<T>
	)
	//~}
}
