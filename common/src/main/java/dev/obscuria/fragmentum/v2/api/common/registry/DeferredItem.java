package dev.obscuria.fragmentum.v2.api.common.registry;

import dev.obscuria.fragmentum.v2.core.common.registry._DeferredItem;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

@SuppressWarnings("unused")
public interface DeferredItem<T extends Item> extends Deferred<T>, ItemLike {

    static <T extends Item> DeferredItem<T> create(Holder<T> holder) {
        return new _DeferredItem<>(holder);
    }

    ItemStack instantiate();
}