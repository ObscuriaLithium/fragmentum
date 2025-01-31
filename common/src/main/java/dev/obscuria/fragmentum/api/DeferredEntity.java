package dev.obscuria.fragmentum.api;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.apache.logging.log4j.util.Lazy;

/**
 * A specialized version of {@link Deferred} designed to handle deferred access
 * to {@link EntityType} instances for entities in Minecraft.
 *
 * @param <T> The specific type of {@link Entity} associated with this {@code DeferredEntity}.
 */
public class DeferredEntity<T extends Entity> extends Deferred<EntityType<?>, EntityType<T>>
{
    /**
     * Constructs a new {@code DeferredEntity} with a {@link Lazy} holder of an {@link EntityType}.
     *
     * @param lazyHolder A {@link Lazy} holder that provides deferred access to a {@link Holder} of {@link EntityType}.
     */
    public DeferredEntity(Lazy<Holder<EntityType<?>>> lazyHolder)
    {
        super(lazyHolder);
    }
}