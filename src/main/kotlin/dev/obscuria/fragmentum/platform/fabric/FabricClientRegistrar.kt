package dev.obscuria.fragmentum.platform.fabric

//? fabric {
/*import dev.obscuria.fragmentum.api.client.ClientRegistrar
import dev.obscuria.fragmentum.api.registry.Deferred
import dev.obscuria.fragmentum.client.TooltipComponentRegistry
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
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
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal data class FabricClientRegistrar(val modid: String) : ClientRegistrar {

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
		EntityRendererRegistry.register(type.get(), provider)
	}

	override fun <T : BlockEntity> registerBlockEntityRenderer(
		type: Deferred<BlockEntityType<T>>,
		provider: BlockEntityRendererProvider<T>
	) {
		BlockEntityRenderers.register(type.get(), provider)
	}

	override fun <T : ParticleOptions> registerParticleRenderer(
        type: Deferred<ParticleType<T>>,
        provider: ParticleProvider<T>
	) {
		ParticleFactoryRegistry.getInstance().register(type.get(), provider)
	}

	override fun <T : ParticleOptions> registerTexturedParticleRenderer(
        type: Deferred<ParticleType<T>>,
        provider: ClientRegistrar.TexturedParticleProvider<T>
	) {
		ParticleFactoryRegistry.getInstance().register(type.get(), provider::create)
	}

	override fun registerModelLayer(
		location: ModelLayerLocation,
		provider: ClientRegistrar.ModelLayerProvider
	) {
		EntityModelLayerRegistry.registerModelLayer(location, provider::create);
	}

	override fun registerItemColor(
		provider: ClientRegistrar.ItemColorProvider,
		vararg items: Deferred<out Item>
	) {
		ColorProviderRegistry.ITEM.register(provider::pick, *items.map { it.get() }.toTypedArray())
	}

	override fun registerBlockColor(
		provider: ClientRegistrar.BlockColorProvider,
		vararg blocks: Deferred<out Block>
	) {
		ColorProviderRegistry.BLOCK.register(provider::pick, *blocks.map { it.get() }.toTypedArray())
	}

	override fun registerItemProperty(
		key: ResourceLocation,
		function: ClampedItemPropertyFunction
	) {
		ItemProperties.registerGeneric(key, function)
	}
}
*///?}
