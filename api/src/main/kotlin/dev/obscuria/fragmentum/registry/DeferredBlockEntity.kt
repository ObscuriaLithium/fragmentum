@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.registry

import net.minecraft.core.Holder
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType

class DeferredBlockEntity<T : BlockEntity>(supplier: () -> Holder<BlockEntityType<*>>) :
    Deferred<BlockEntityType<*>, BlockEntityType<T>>(supplier)