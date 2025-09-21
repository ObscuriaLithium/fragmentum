@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.registry

import net.minecraft.core.Holder
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike

class DeferredItem<T : Item>(supplier: () -> Holder<Item>) : Deferred<Item, T>(supplier), ItemLike {

    override fun asItem(): Item = value

    fun instantiate(): ItemStack = asItem().defaultInstance
}