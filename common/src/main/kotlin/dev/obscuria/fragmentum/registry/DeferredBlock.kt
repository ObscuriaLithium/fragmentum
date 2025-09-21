@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.registry

import net.minecraft.core.Holder
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState

class DeferredBlock<T : Block>(
    supplier: () -> Holder<Block>
) : Deferred<Block, T>(supplier) {

    fun instantiate(): BlockState = get().defaultBlockState()
}