package dev.obscuria.fragmentum.api;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import org.apache.logging.log4j.util.Lazy;

/**
 * A specialized version of {@link Deferred} designed to handle deferred access
 * to {@link MobEffect} instances, such as those used for applying effects to entities in Minecraft.
 *
 * @param <T> The specific type of {@link MobEffect} associated with this {@code DeferredMobEffect}.
 */
public class DeferredMobEffect<T extends MobEffect> extends Deferred<MobEffect, T>
{
    /**
     * Constructs a new {@code DeferredMobEffect} with a {@link Lazy} holder of a {@link MobEffect}.
     *
     * @param lazyHolder A {@link Lazy} holder that provides deferred access to a {@link Holder} of {@link MobEffect}.
     */
    public DeferredMobEffect(Lazy<Holder<MobEffect>> lazyHolder)
    {
        super(lazyHolder);
    }
}