package dev.obscuria.fragmentum.config.screen

import dev.isxander.yacl3.api.ConfigCategory
import dev.isxander.yacl3.api.LabelOption
import dev.isxander.yacl3.api.ListOption
import dev.isxander.yacl3.api.Option
import dev.obscuria.fragmentum.api.config.screen.ConfigCategoryBuilder
import dev.obscuria.fragmentum.api.config.screen.ConfigGroupBuilder
import dev.obscuria.fragmentum.api.config.screen.OptionBuilder
import net.minecraft.network.chat.Component
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class ConfigCategoryBuilderImpl(
	name: Component
) : ConfigCategoryBuilder {

	val categoryBuilder: ConfigCategory.Builder = ConfigCategory.createBuilder().name(name)

	override fun label(component: Component): ConfigCategoryBuilder {
		categoryBuilder.option(LabelOption.create(component))
		return this
	}

	override fun group(builder: ConfigGroupBuilder): ConfigCategoryBuilder {
		if (builder !is ConfigGroupBuilderImpl) return this
		categoryBuilder.group(builder.build())
		return this
	}

	override fun option(builder: OptionBuilder<*, *>): ConfigCategoryBuilder {
		when (val option = builder.build()) {
			is ListOption<*> -> categoryBuilder.group(option)
			is Option<*> -> categoryBuilder.option(option)
		}
		return this
	}

	fun build(): ConfigCategory {
		return categoryBuilder.build()
	}
}
