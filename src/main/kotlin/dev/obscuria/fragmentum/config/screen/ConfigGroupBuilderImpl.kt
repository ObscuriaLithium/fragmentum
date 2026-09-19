package dev.obscuria.fragmentum.config.screen

import dev.isxander.yacl3.api.Option
import dev.isxander.yacl3.api.OptionDescription
import dev.isxander.yacl3.api.OptionGroup
import dev.obscuria.fragmentum.api.config.screen.ConfigGroupBuilder
import dev.obscuria.fragmentum.api.config.screen.OptionBuilder
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class ConfigGroupBuilderImpl(
	name: Component?,
	desc: Component?,
	image: ResourceLocation?
) : ConfigGroupBuilder {

	val groupBuilder: OptionGroup.Builder = OptionGroup.createBuilder()

	init {
		name?.let { groupBuilder.name(it) }
		desc?.let {
			val descBuilder = OptionDescription.createBuilder()
			descBuilder.text(it)
			image?.let(descBuilder::webpImage)
			groupBuilder.description(descBuilder.build())
		}
	}

	override fun option(builder: OptionBuilder<*, *>): ConfigGroupBuilder {
		when (val option = builder.build()) {
			is Option<*> -> groupBuilder.option(option)
		}
		return this
	}

	fun build(): OptionGroup {
		return groupBuilder.build()
	}
}
