package dev.obscuria.fragmentum.api;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import org.apache.logging.log4j.util.Lazy;

/**
 * A specialized version of {@link Deferred} designed to handle deferred access
 * to {@link Attribute} instances, such as those used for entity attributes in Minecraft.
 */
public class DeferredAttribute extends Deferred<Attribute, Attribute>
{
    /**
     * Constructs a new {@code DeferredAttribute} with a {@link Lazy} holder of an {@link Attribute}.
     *
     * @param lazyHolder A {@link Lazy} holder that provides deferred access to a {@link Holder} of {@link Attribute}.
     */
    public DeferredAttribute(Lazy<Holder<Attribute>> lazyHolder)
    {
        super(lazyHolder);
    }
}