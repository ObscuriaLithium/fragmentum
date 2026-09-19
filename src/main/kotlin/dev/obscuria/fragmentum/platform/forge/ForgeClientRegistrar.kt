package dev.obscuria.fragmentum.platform.forge

//? forge {
import dev.obscuria.fragmentum.api.client.ClientRegistrar
import dev.obscuria.fragmentum.api.registry.Deferred
import dev.obscuria.fragmentum.client.TooltipComponentRegistry
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.inventory.tooltip.TooltipComponent
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.client.event.RegisterColorHandlersEvent
import net.minecraftforge.client.event.RegisterParticleProvidersEvent
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal data class ForgeClientRegistrar(val modId: String) : ClientRegistrar {

	override fun <T : TooltipComponent> registerTooltipComponent(
		type: Class<T>,
		factory: (T) -> ClientTooltipComponent
	) {
		TooltipComponentRegistry.register(type, factory)
	}

	override fun <T : Entity> registerEntityRenderer(
		type: Deferred<EntityType<T>>,
		provider: EntityRendererProvider<T>
	) {
		ForgeEntrypoint.addListener<EntityRenderersEvent.RegisterRenderers>(modId) {
			it.registerEntityRenderer(type.get(), provider)
		}
	}

	override fun <T : BlockEntity> registerBlockEntityRenderer(
		type: Deferred<BlockEntityType<T>>,
		provider: BlockEntityRendererProvider<T>
	) {
		ForgeEntrypoint.addListener<EntityRenderersEvent.RegisterRenderers>(modId) {
			it.registerBlockEntityRenderer(type.get(), provider)
		}
	}

	override fun <T : ParticleOptions> registerParticleRenderer(
		type: Deferred<ParticleType<T>>,
		provider: ParticleProvider<T>
	) {
		ForgeEntrypoint.addListener<RegisterParticleProvidersEvent>(modId) {
			it.registerSpecial(type.get(), provider)
		}
	}

	override fun <T : ParticleOptions> registerTexturedParticleRenderer(
		type: Deferred<ParticleType<T>>,
		provider: ClientRegistrar.TexturedParticleProvider<T>
	) {
		ForgeEntrypoint.addListener<RegisterParticleProvidersEvent>(modId) {
			it.registerSpriteSet(type.get(), provider::create)
		}
	}

	override fun registerModelLayer(
		location: ModelLayerLocation,
		provider: ClientRegistrar.ModelLayerProvider
	) {
		ForgeEntrypoint.addListener<EntityRenderersEvent.RegisterLayerDefinitions>(modId) {
			it.registerLayerDefinition(location, provider::create)
		}
	}

	override fun registerItemColor(
		provider: ClientRegistrar.ItemColorProvider,
		vararg items: Deferred<out Item>
	) {
		ForgeEntrypoint.addListener<RegisterColorHandlersEvent.Item>(modId) {
			val itemArray = items.map { deferred -> deferred.get() }.toTypedArray()
			it.register(provider::pick, *itemArray)
		}
	}

	override fun registerBlockColor(
		provider: ClientRegistrar.BlockColorProvider,
		vararg blocks: Deferred<out Block>
	) {
		ForgeEntrypoint.addListener<RegisterColorHandlersEvent.Block>(modId) {
			val blockArray = blocks.map { deferred -> deferred.get() }.toTypedArray()
			it.register(provider::pick, *blockArray)
		}
	}

	override fun registerItemProperty(
		key: ResourceLocation,
		function: ClampedItemPropertyFunction
	) {
		ItemProperties.registerGeneric(key, function)
	}
}
//?}
