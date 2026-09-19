package dev.obscuria.fragmentum.config.screen

import dev.isxander.yacl3.api.YetAnotherConfigLib
import dev.obscuria.fragmentum.api.config.screen.ConfigCategoryBuilder
import dev.obscuria.fragmentum.api.config.screen.ConfigScreenBuilder
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class ConfigScreenBuilderImpl(
	val modId: String
) : ConfigScreenBuilder {

	val screenBuilder: YetAnotherConfigLib.Builder = YetAnotherConfigLib.createBuilder()

	override fun title(component: Component): ConfigScreenBuilder {
		screenBuilder.title(component)
		return this
	}

	override fun category(builder: ConfigCategoryBuilder): ConfigScreenBuilder {
		if (builder !is ConfigCategoryBuilderImpl) return this
		screenBuilder.category(builder.build())
		return this
	}

	override fun save(runnable: Runnable): ConfigScreenBuilder {
		screenBuilder.save(runnable)
		return this
	}

	override fun build(parent: Screen): Screen {
		return screenBuilder.build().generateScreen(parent)
	}
}
