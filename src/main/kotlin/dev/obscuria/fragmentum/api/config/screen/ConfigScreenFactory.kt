package dev.obscuria.fragmentum.api.config.screen

import dev.obscuria.fragmentum.api.config.Configurable
import dev.obscuria.fragmentum.api.config.format.DoubleFormatter
import dev.obscuria.fragmentum.config.screen.ConfigScreenFactoryImpl
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation

interface ConfigScreenFactory {

	fun createScreen(): ConfigScreenBuilder

	fun category(
		key: String
	): ConfigCategoryBuilder

	fun category(
		name: Component
	): ConfigCategoryBuilder

	fun group(
		key: String
	): ConfigGroupBuilder

	fun group(
		name: Component
	): ConfigGroupBuilder

	fun group(
		name: Component,
		desc: Component
	): ConfigGroupBuilder

	fun group(
		key: String,
		webpImage: ResourceLocation
	): ConfigGroupBuilder

	fun group(
		name: Component,
		webpImage: ResourceLocation
	): ConfigGroupBuilder

	fun group(
		name: Component,
		desc: Component,
		webpImage: ResourceLocation
	): ConfigGroupBuilder

	fun <T> option(
		configurable: Configurable<T>
	): OptionBuilder<T, *> where T : Any

	fun <T> listOption(
		configurable: Configurable<List<T>>
	): OptionBuilder<T, *> where T : Any

	fun buttonOption(
		text: Component? = null,
		available: Boolean = true,
		action: (Screen) -> Unit
	): OptionBuilder<Unit, *>

	fun checkBoxOption(
		configurable: Configurable<Boolean>
	): OptionBuilder<Boolean, *>

	fun intFieldOption(
		configurable: Configurable<Int>,
		min: Int,
		max: Int
	): OptionBuilder<Int, *>

	fun doubleFieldOption(
		configurable: Configurable<Double>,
		min: Double,
		max: Double
	): OptionBuilder<Double, *>

	fun intSliderOption(
		configurable: Configurable<Int>,
		step: Int,
		min: Int,
		max: Int,
		formatter: DoubleFormatter
	): OptionBuilder<Int, *>

	fun doubleSliderOption(
		configurable: Configurable<Double>,
		step: Double,
		min: Double,
		max: Double,
		formatter: DoubleFormatter
	): OptionBuilder<Double, *>

	fun <E> enumCycleOption(
		configurable: Configurable<E>,
		enumClass: Class<E>
	): OptionBuilder<E, *> where E : Enum<E>

	fun stringListOption(
		configurable: Configurable<List<String>>
	): OptionBuilder<String, *>

	fun dropdownStringListOption(
		configurable: Configurable<List<String>>,
		values: () -> List<String>,
		allowAnyValue: Boolean = false,
		allowEmptyValue: Boolean = false
	): OptionBuilder<String, *>

	companion object {

		fun create(modId: String): ConfigScreenFactory {
			return ConfigScreenFactoryImpl(modId)
		}
	}
}
