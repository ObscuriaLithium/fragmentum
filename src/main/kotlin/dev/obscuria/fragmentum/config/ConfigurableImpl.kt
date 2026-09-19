package dev.obscuria.fragmentum.config

import dev.obscuria.fragmentum.api.config.Configurable

//? <=1.20.1
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
//? >1.20.1
//import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal data class ConfigurableImpl<T>(
	override val name: String,
	val delegate: ConfigValue<T>
) : Configurable<T> where T : Any {

	override val default: T
		get() = delegate.default

	override fun get(): T {
		return delegate.get()
	}

	override fun set(value: T) {
		this.delegate.set(value)
	}

	override fun save() {
		this.delegate.save()
	}
}
