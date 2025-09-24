package dev.obscuria.fragmentum.registry;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class DeferredItem<T extends Item> extends Deferred<Item, T> implements ItemLike
{
    public DeferredItem(Supplier<Holder<Item>> supplier)
    {
        super(supplier);
    }

    @Override
    public Item asItem()
    {
        throw new NotImplementedException();
    }

    public ItemStack instantiate()
    {
        throw new NotImplementedException();
    }
}
