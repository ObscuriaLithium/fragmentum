package dev.obscuria.fragmentum.api.config

import dev.obscuria.fragmentum.config.ConfigBuilderImpl

interface ConfigBuilder {

	fun comment(comment: String): ConfigBuilder

	fun comment(vararg comment: String): ConfigBuilder

	fun pushCategory(name: String)

	fun popCategory()

	fun <T> define(
		path: String,
		default: T
	): Configurable<T> where T : Any

	fun defineBool(
		path: String,
		default: Boolean
	): Configurable<Boolean>

	fun defineInt(
		path: String,
		default: Int,
		min: Int = Int.MIN_VALUE,
		max: Int = Int.MAX_VALUE
	): Configurable<Int>

	fun defineDouble(
		path: String,
		default: Double,
		min: Double = Double.MIN_VALUE,
		max: Double = Double.MAX_VALUE
	): Configurable<Double>

	fun defineString(
		path: String,
		default: String
	): Configurable<String>

	fun <T : Enum<T>> defineEnum(
		path: String,
		default: T
	): Configurable<T>

	fun defineList(
		path: String,
		default: List<String>
	): Configurable<List<String>>

	fun buildClient(modId: String): Set<Configurable<*>>

	fun buildCommon(modId: String): Set<Configurable<*>>

	fun buildServer(modId: String): Set<Configurable<*>>

	companion object {

		fun create(fileName: String): ConfigBuilder {
			return ConfigBuilderImpl(fileName)
		}
	}
}
