package dev.obscuria.fragmentum.config

import com.google.common.base.Predicates
import dev.obscuria.fragmentum.api.config.ConfigBuilder
import dev.obscuria.fragmentum.api.config.Configurable
import org.jetbrains.annotations.ApiStatus

//? <=1.20.1 {
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.common.ForgeConfigSpec.Builder
//?} else {
/*import net.neoforged.fml.config.ModConfig
import net.neoforged.neoforge.common.ModConfigSpec.Builder
*///?}

@ApiStatus.Internal
internal class ConfigBuilderImpl(
	val fileName: String
) : ConfigBuilder {

	val values: MutableSet<Configurable<*>> = mutableSetOf()
	val specBuilder: Builder = Builder()

	override fun comment(comment: String): ConfigBuilder {
		this.specBuilder.comment(comment)
		return this
	}

	override fun comment(vararg comment: String): ConfigBuilder {
		this.specBuilder.comment(*comment)
		return this
	}

	override fun pushCategory(name: String) {
		this.specBuilder.push(name)
	}

	override fun popCategory() {
		this.specBuilder.pop()
	}

	override fun <T> define(path: String, default: T): Configurable<T> where T : Any {
		val value = specBuilder.define(path, default)
		return register(ConfigurableImpl(path, value))
	}

	override fun defineBool(path: String, default: Boolean): Configurable<Boolean> {
		val value = specBuilder.define(path, default)
		return register(ConfigurableImpl(path, value))
	}

	override fun defineInt(path: String, default: Int, min: Int, max: Int): Configurable<Int> {
		val value = specBuilder.defineInRange(path, default, min, max)
		return register(ConfigurableImpl(path, value))
	}

	override fun defineDouble(path: String, default: Double, min: Double, max: Double): Configurable<Double> {
		val value = specBuilder.defineInRange(path, default, min, max)
		return register(ConfigurableImpl(path, value))
	}

	override fun defineString(path: String, default: String): Configurable<String> {
		val value = specBuilder.define(path, default)
		return register(ConfigurableImpl(path, value))
	}

	override fun <T : Enum<T>> defineEnum(path: String, default: T): Configurable<T> {
		val value = specBuilder.defineEnum(path, default)
		return register(ConfigurableImpl(path, value))
	}

	@Suppress("DEPRECATION")
	override fun defineList(path: String, default: List<String>): Configurable<List<String>> {
		val value = specBuilder.defineListAllowEmpty(listOf(path), { default }, Predicates.alwaysTrue())
		return register(ConfigurableImpl(path, value))
	}

	override fun buildClient(modId: String): Set<Configurable<*>> {
		this.buildInternal(ModConfig.Type.CLIENT, modId)
		return values
	}

	override fun buildCommon(modId: String): Set<Configurable<*>> {
		this.buildInternal(ModConfig.Type.COMMON, modId)
		return values
	}

	override fun buildServer(modId: String): Set<Configurable<*>> {
		this.buildInternal(ModConfig.Type.SERVER, modId)
		return values
	}

	//? if fabric && <=1.20.1 {
	/*private fun buildInternal(type: ModConfig.Type, modId: String) {
		val spec = specBuilder.build()
		fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry.INSTANCE
			.register(modId, type, spec, fileName)
	}
	*///?} elif fabric && >1.20.1 {
	/*private fun buildInternal(type: ModConfig.Type, modId: String) {
		val spec = specBuilder.build()
		fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry.INSTANCE
			.register(modId, type, spec, fileName)
	}
	*///?} elif forge {
	private fun buildInternal(type: ModConfig.Type, modId: String) {
		val spec = specBuilder.build()
		net.minecraftforge.fml.ModLoadingContext.get()
			.registerConfig(type, spec, fileName)
	}
	//?} elif neoforge {
	/*private fun buildInternal(type: ModConfig.Type, modId: String) {
		val spec = specBuilder.build()
		net.neoforged.fml.ModLoadingContext.get().activeContainer
			.registerConfig(type, spec, fileName)
	}
	*///?}

	private fun <T> register(value: Configurable<T>): Configurable<T> where T : Any {
		if (values.contains(value)) throw IllegalStateException("Duplicate value: $value")
		this.values.add(value)
		return value
	}
}
