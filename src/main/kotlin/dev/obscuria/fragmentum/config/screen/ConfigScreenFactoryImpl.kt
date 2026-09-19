package dev.obscuria.fragmentum.config.screen

import dev.isxander.yacl3.api.controller.*
import dev.obscuria.fragmentum.api.config.Configurable
import dev.obscuria.fragmentum.api.config.format.DoubleFormatter
import dev.obscuria.fragmentum.api.config.screen.ConfigCategoryBuilder
import dev.obscuria.fragmentum.api.config.screen.ConfigGroupBuilder
import dev.obscuria.fragmentum.api.config.screen.ConfigScreenBuilder
import dev.obscuria.fragmentum.api.config.screen.ConfigScreenFactory
import dev.obscuria.fragmentum.api.config.screen.OptionBuilder
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.resources.ResourceLocation
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal class ConfigScreenFactoryImpl(
	val modId: String
) : ConfigScreenFactory {

	override fun createScreen(): ConfigScreenBuilder {
		return ConfigScreenBuilderImpl(modId)
	}

	override fun category(
		key: String
	): ConfigCategoryBuilder {
		return category(translate(modId, "category", key))
	}

	override fun category(
		name: Component
	): ConfigCategoryBuilder {
		return ConfigCategoryBuilderImpl(name)
	}

	override fun group(
		key: String
	): ConfigGroupBuilder {
		val name = translate(modId, "group", key)
		val desc = translate(modId, "group", key, "desc").withStyle(ChatFormatting.GRAY)
		return ConfigGroupBuilderImpl(name, desc, null)
	}

	override fun group(
		name: Component
	): ConfigGroupBuilder {
		return ConfigGroupBuilderImpl(name, null, null)
	}

	override fun group(
		name: Component,
		desc: Component
	): ConfigGroupBuilder {
		return ConfigGroupBuilderImpl(name, desc, null)
	}

	override fun group(
		key: String,
		webpImage: ResourceLocation
	): ConfigGroupBuilder {
		val name = translate(modId, "group", key)
		val desc = translate(modId, "group", key, "desc").withStyle(ChatFormatting.GRAY)
		return ConfigGroupBuilderImpl(name, desc, webpImage)
	}

	override fun group(
		name: Component,
		webpImage: ResourceLocation
	): ConfigGroupBuilder {
		return ConfigGroupBuilderImpl(name, null, webpImage)
	}

	override fun group(
		name: Component,
		desc: Component,
		webpImage: ResourceLocation
	): ConfigGroupBuilder {
		return ConfigGroupBuilderImpl(name, desc, webpImage)
	}

	override fun <T : Any> option(
		configurable: Configurable<T>
	): OptionBuilder<T, *> {
		return ValueOptionBuilderImpl(modId, configurable)
	}

	override fun <T : Any> listOption(
		configurable: Configurable<List<T>>
	): OptionBuilder<T, *> {
		return ListOptionBuilderImpl(modId, configurable)
	}

	override fun checkBoxOption(
		configurable: Configurable<Boolean>
	): OptionBuilder<Boolean, *> {
		return option(configurable).controller {
			TickBoxControllerBuilder.create(it)
		}
	}

	override fun intFieldOption(
		configurable: Configurable<Int>,
		min: Int,
		max: Int
	): OptionBuilder<Int, *> {
		return option(configurable).controller {
			IntegerFieldControllerBuilder.create(it)
				.range(min, max)
		}
	}

	override fun doubleFieldOption(
		configurable: Configurable<Double>,
		min: Double,
		max: Double
	): OptionBuilder<Double, *> {
		return option(configurable).controller {
			DoubleFieldControllerBuilder.create(it)
				.range(min, max)
		}
	}

	override fun intSliderOption(
		configurable: Configurable<Int>,
		step: Int,
		min: Int,
		max: Int,
		formatter: DoubleFormatter
	): OptionBuilder<Int, *> {
		return option(configurable).controller {
			IntegerSliderControllerBuilder.create(it)
				.step(step).range(min, max)
				.formatValue(formatter::formatInt)
		}
	}

	override fun doubleSliderOption(
		configurable: Configurable<Double>,
		step: Double,
		min: Double,
		max: Double,
		formatter: DoubleFormatter
	): OptionBuilder<Double, *> {
		return option(configurable).controller {
			DoubleSliderControllerBuilder.create(it)
				.step(step).range(min, max)
				.formatValue(formatter::format)
		}
	}

	override fun <E : Enum<E>> enumCycleOption(
		configurable: Configurable<E>,
		enumClass: Class<E>
	): OptionBuilder<E, *> {
		return option(configurable).controller {
			EnumControllerBuilder.create(it).enumClass(enumClass)
		}
	}

	override fun stringListOption(
		configurable: Configurable<List<String>>
	): OptionBuilder<String, *> {
		return listOption(configurable).controller {
			StringControllerBuilder.create(it)
		}.initial("")
	}

	override fun dropdownStringListOption(
		configurable: Configurable<List<String>>,
		values: () -> List<String>,
		allowAnyValue: Boolean,
		allowEmptyValue: Boolean
	): OptionBuilder<String, *> {
		return listOption(configurable).controller {
			DropdownStringControllerBuilder.create(it)
				.values(values.invoke())
				.allowAnyValue(allowAnyValue)
				.allowEmptyValue(allowEmptyValue)
		}.initial("")
	}

	companion object {

		fun translate(modId: String, prefix: String, key: String): MutableComponent {
			return Component.translatable("config.$modId.$prefix.$key")
		}

		fun translate(modId: String, prefix: String, key: String, suffix: String): MutableComponent {
			return Component.translatable("config.$modId.$prefix.$key.$suffix")
		}
	}
}
