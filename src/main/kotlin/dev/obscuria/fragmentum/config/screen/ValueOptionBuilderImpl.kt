package dev.obscuria.fragmentum.config.screen

import dev.isxander.yacl3.api.Binding
import dev.isxander.yacl3.api.Option
import dev.isxander.yacl3.dsl.controller
import dev.obscuria.fragmentum.api.config.Configurable
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class ValueOptionBuilderImpl<T>(
	modId: String,
	val configurable: Configurable<T>
) : OptionBuilderImpl<T, Option<T>>(modId) where T : Any {

	init {
		nameKey = configurable.name
		descKey = configurable.name
	}

	override fun build(): Option<T> {
		val builder = Option.createBuilder<T>()
		builder.name(resolveName())
		resolveDesc()?.let(builder::description)
		controllerFactory?.let(builder::controller)
		builder.binding(
			Binding.generic(
				configurable.default,
				configurable::get,
				configurable::set
			)
		)
		return builder.build()
	}
}
