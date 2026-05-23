package dev.obscuria.fragmentum.v2.core.common.registry;

import dev.obscuria.fragmentum.v2.api.common.registry.DeferredItem;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class _DeferredItem<T extends Item> extends _Deferred<T> implements DeferredItem<T> {

    public _DeferredItem(Holder<T> holder) {
        super(holder);
    }

    @Override public Item asItem() {
        return get();
    }

    @Override public ItemStack instantiate() {
        return asItem().getDefaultInstance();
    }
}