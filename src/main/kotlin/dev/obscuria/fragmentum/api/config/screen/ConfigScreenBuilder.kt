package dev.obscuria.fragmentum.api.config.screen

import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component

interface ConfigScreenBuilder {

	fun title(component: Component): ConfigScreenBuilder

	fun category(builder: ConfigCategoryBuilder): ConfigScreenBuilder

	fun save(runnable: Runnable): ConfigScreenBuilder

	fun build(parent: Screen): Screen
}
