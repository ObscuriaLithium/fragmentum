package dev.obscuria.fragmentum.api.config.screen

interface ConfigGroupBuilder {

	fun option(builder: OptionBuilder<*, *>): ConfigGroupBuilder
}
