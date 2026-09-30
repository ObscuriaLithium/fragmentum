package dev.obscuria.fragmentum.config.screen

import dev.isxander.yacl3.api.ButtonOption
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class ButtonOptionBuilderImpl(
	modId: String,
	val text: Component?,
	val available: Boolean,
	val action: (Screen) -> Unit,
) : OptionBuilderImpl<Unit, ButtonOption>(modId) {

	override fun build(): ButtonOption {
		val builder = ButtonOption.createBuilder()
		text?.let(builder::text)
		builder.name(resolveName())
		resolveDesc()?.let(builder::description)
		builder.action { screen, _ -> action(screen) }
		builder.available(available)
		return builder.build()
	}
}
