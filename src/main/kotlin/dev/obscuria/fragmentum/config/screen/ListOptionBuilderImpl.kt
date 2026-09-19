package dev.obscuria.fragmentum.config.screen

import dev.isxander.yacl3.api.Binding
import dev.isxander.yacl3.api.ListOption
import dev.obscuria.fragmentum.api.config.Configurable
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class ListOptionBuilderImpl<T>(
	modId: String,
	val configurable: Configurable<List<T>>
) : OptionBuilderImpl<T, ListOption<T>>(modId) where T : Any {

	init {
		nameKey = configurable.name
		descKey = configurable.name
	}

	override fun build(): ListOption<T> {
		val builder = ListOption.createBuilder<T>()
		initialValue?.let(builder::initial)
		builder.name(resolveName())
		resolveDesc()?.let(builder::description)
		controllerFactory?.let { builder.controller(it) }
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
