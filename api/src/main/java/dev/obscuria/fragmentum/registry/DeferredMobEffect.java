package dev.obscuria.fragmentum.registry;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class DeferredMobEffect<T extends MobEffect> extends Deferred<MobEffect, T>
{
    public DeferredMobEffect(Supplier<Holder<MobEffect>> supplier)
    {
        super(supplier);
    }
}
