package dev.obscuria.fragmentum.api.registry

import net.minecraft.core.Holder
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import java.util.function.Supplier

class DeferredItem<T : Item>(supplier: Supplier<Holder<Item>>)
    : Deferred<Item, T>(supplier), ItemLike
{
    override fun asItem(): T = value()

    fun instance(): ItemStack = value().defaultInstance
}