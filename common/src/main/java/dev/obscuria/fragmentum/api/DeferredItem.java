package dev.obscuria.fragmentum.api;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.apache.logging.log4j.util.Lazy;

/**
 * A specialized version of {@link Deferred} for handling deferred access to {@link Item} instances in Minecraft.
 * This class provides functionality to interact with {@link Item} objects and create their {@link ItemStack} instances.
 *
 * @param <T> The specific type of {@link Item} wrapped by this {@code DeferredItem}.
 */
public class DeferredItem<T extends Item> extends Deferred<Item, T> implements ItemLike
{
    /**
     * Constructs a new {@code DeferredItem} with a {@link Lazy} holder of an {@link Item}.
     *
     * @param lazyHolder A {@link Lazy} holder that provides deferred access to a {@link Holder} of {@link Item}.
     */
    public DeferredItem(Lazy<Holder<Item>> lazyHolder)
    {
        super(lazyHolder);
    }

    /**
     * Retrieves the associated {@link Item} of this deferred item as required by the {@link ItemLike} interface.
     *
     * @return The {@link Item} wrapped by this {@code DeferredItem}.
     */
    @Override
    public Item asItem()
    {
        return value();
    }

    /**
     * Creates a default {@link ItemStack} instance for the {@link Item} wrapped by this {@code DeferredItem}.
     *
     * @return A default {@link ItemStack} for the wrapped {@link Item}.
     */
    public ItemStack instance()
    {
        return value().getDefaultInstance();
    }
}