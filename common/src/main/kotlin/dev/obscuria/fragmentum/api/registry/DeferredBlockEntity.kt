package dev.obscuria.fragmentum.api.registry

import net.minecraft.core.Holder
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import java.util.function.Supplier

class DeferredBlockEntity<T : BlockEntity>(supplier: Supplier<Holder<BlockEntityType<*>>>)
    : Deferred<BlockEntityType<*>, BlockEntityType<T>>(supplier)