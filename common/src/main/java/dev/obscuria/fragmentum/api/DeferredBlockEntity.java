package dev.obscuria.fragmentum.api;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.apache.logging.log4j.util.Lazy;

/**
 * A specialized version of {@link Deferred} designed to handle deferred access
 * to {@link BlockEntityType} instances for block entities in Minecraft.
 *
 * @param <T> The specific type of {@link BlockEntity} associated with this {@code DeferredBlockEntity}.
 */
public class DeferredBlockEntity<T extends BlockEntity> extends Deferred<BlockEntityType<?>, BlockEntityType<T>>
{
    /**
     * Constructs a new {@code DeferredBlockEntity} with a {@link Lazy} holder of a {@link BlockEntityType}.
     *
     * @param lazyHolder A {@link Lazy} holder that provides deferred access to a {@link Holder} of {@link BlockEntityType}.
     */
    public DeferredBlockEntity(Lazy<Holder<BlockEntityType<?>>> lazyHolder)
    {
        super(lazyHolder);
    }
}