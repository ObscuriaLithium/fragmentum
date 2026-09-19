package dev.obscuria.fragmentum.api.registry

import dev.obscuria.fragmentum.registry.DeferredImpl
import net.minecraft.core.Holder
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import java.util.function.Supplier

interface Deferred<T> : Supplier<T> {

	fun isBound(): Boolean

	fun holder(): Holder<T>

	fun <V> `as`(): Deferred<V>

	companion object {

		fun <T> create(supplier: () -> Holder<T>): Deferred<T> {
			return DeferredImpl(supplier)
		}
	}
}

fun Deferred<Item>.instantiate(): ItemStack {
	return this.get().defaultInstance
}

fun Deferred<Block>.instantiate(): BlockState {
	return this.get().defaultBlockState()
}

fun Deferred<ParticleType<*>>.asSimple(): SimpleParticleType {
	return this.get() as SimpleParticleType
}
