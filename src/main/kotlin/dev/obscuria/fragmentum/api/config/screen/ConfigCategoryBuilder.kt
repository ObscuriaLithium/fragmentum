package dev.obscuria.fragmentum.api.config.screen

import net.minecraft.network.chat.Component

interface ConfigCategoryBuilder {

	fun label(component: Component): ConfigCategoryBuilder

	fun group(builder: ConfigGroupBuilder): ConfigCategoryBuilder

	fun option(builder: OptionBuilder<*, *>): ConfigCategoryBuilder
}
