package dev.obscuria.fragmentum.config.screen

import dev.isxander.yacl3.api.Option
import dev.isxander.yacl3.api.OptionDescription
import dev.isxander.yacl3.api.controller.ControllerBuilder
import dev.obscuria.fragmentum.api.config.screen.OptionBuilder
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal abstract class OptionBuilderImpl<T, R>(
	val modId: String
) : OptionBuilder<T, R> where T : Any {

	var initialValue: T? = null
	var controllerFactory: ((Option<T>) -> ControllerBuilder<T>)? = null
	var nameKey: String = ""
	var descKey: String = ""
	var nameOverride: Component? = null
	var descOverride: Component? = null
	var descWrapperKey: String? = null
	var descImage: ResourceLocation? = null
	var noDesc: Boolean = false

	override fun initial(initial: T): OptionBuilder<T, R> {
		initialValue = initial
		return this
	}

	override fun key(key: String): OptionBuilder<T, R> {
		nameKey = key
		descKey = key
		return this
	}

	override fun name(key: String): OptionBuilder<T, R> {
		nameKey = key
		return this
	}

	override fun desc(key: String): OptionBuilder<T, R> {
		descKey = key
		return this
	}

	override fun name(component: Component): OptionBuilder<T, R> {
		nameOverride = component
		return this
	}

	override fun desc(component: Component): OptionBuilder<T, R> {
		descOverride = component
		return this
	}

	override fun noDesc(): OptionBuilder<T, R> {
		noDesc = true
		return this
	}

	override fun webpImage(texture: ResourceLocation): OptionBuilder<T, R> {
		descImage = texture
		return this
	}

	override fun anyReloadHint(): OptionBuilder<T, R> {
		descWrapperKey = "config.fragmentum.anyReloadWrapper"
		return this
	}

	override fun worldReloadHint(): OptionBuilder<T, R> {
		descWrapperKey = "config.fragmentum.worldReloadWrapper"
		return this
	}

	override fun controller(factory: (Option<T>) -> ControllerBuilder<T>): OptionBuilder<T, R> {
		controllerFactory = factory
		return this
	}

	protected fun resolveName(): Component {
		return nameOverride ?: Component.translatable("config.${modId}.option.${nameKey}")
	}

	protected fun resolveDesc(): OptionDescription? {
		if (noDesc) return null
		val descBuilder = OptionDescription.createBuilder()
		val desc = descOverride ?: Component
			.translatable("config.${modId}.option.${descKey}.desc")
			.withStyle(ChatFormatting.GRAY)
		descBuilder.text(descWrapperKey?.let { Component.translatable(it, desc) } ?: desc)
		descImage?.let(descBuilder::webpImage)
		return descBuilder.build()
	}
}
