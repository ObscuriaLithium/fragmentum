package dev.obscuria.fragmentum.api.client

import dev.obscuria.fragmentum.api.registry.Deferred
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.inventory.tooltip.TooltipComponent
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockAndTintGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

interface ClientRegistrar {

	fun <T : TooltipComponent> registerTooltipComponent(
		type: Class<T>,
		factory: (T) -> ClientTooltipComponent
	)

	fun <T : Entity> registerEntityRenderer(
		type: Deferred<EntityType<T>>,
		provider: EntityRendererProvider<T>
	)

	fun <T : BlockEntity> registerBlockEntityRenderer(
		type: Deferred<BlockEntityType<T>>,
		provider: BlockEntityRendererProvider<T>
	)

	fun <T : ParticleOptions> registerParticleRenderer(
		type: Deferred<ParticleType<T>>,
		provider: ParticleProvider<T>
	)

	fun <T : ParticleOptions> registerTexturedParticleRenderer(
		type: Deferred<ParticleType<T>>,
		provider: TexturedParticleProvider<T>
	)

	fun registerModelLayer(
		location: ModelLayerLocation,
		provider: ModelLayerProvider
	)

	fun registerItemColor(
		provider: ItemColorProvider,
		vararg items: Deferred<out Item>
	)

	fun registerBlockColor(
		provider: BlockColorProvider,
		vararg blocks: Deferred<out Block>
	)

	fun registerItemProperty(
		key: ResourceLocation,
		function: ClampedItemPropertyFunction
	)

	fun interface TexturedParticleProvider<T : ParticleOptions> {
		fun create(spriteSet: SpriteSet): ParticleProvider<T>
	}

	fun interface ModelLayerProvider {
		fun create(): LayerDefinition
	}

	fun interface ItemColorProvider {
		fun pick(stack: ItemStack, layer: Int): Int
	}

	fun interface BlockColorProvider {
		fun pick(state: BlockState, getter: BlockAndTintGetter?, pos: BlockPos?, layer: Int): Int
	}
}

