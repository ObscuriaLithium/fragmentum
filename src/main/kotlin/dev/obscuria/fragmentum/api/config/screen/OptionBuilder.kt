package dev.obscuria.fragmentum.api.config.screen

import dev.isxander.yacl3.api.Option
import dev.isxander.yacl3.api.controller.ControllerBuilder
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation

interface OptionBuilder<T, R> {

	fun initial(initial: T): OptionBuilder<T, R>

	fun controller(factory: (Option<T>) -> ControllerBuilder<T>): OptionBuilder<T, R>

	fun key(key: String): OptionBuilder<T, R>

	fun name(key: String): OptionBuilder<T, R>

	fun desc(key: String): OptionBuilder<T, R>

	fun name(component: Component): OptionBuilder<T, R>

	fun desc(component: Component): OptionBuilder<T, R>

	fun noDesc(): OptionBuilder<T, R>

	fun webpImage(texture: ResourceLocation): OptionBuilder<T, R>

	fun anyReloadHint(): OptionBuilder<T, R>

	fun worldReloadHint(): OptionBuilder<T, R>

	fun build(): R
}
