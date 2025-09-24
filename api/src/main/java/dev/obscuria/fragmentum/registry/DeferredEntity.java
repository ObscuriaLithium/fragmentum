package dev.obscuria.fragmentum.registry;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class DeferredEntity<T extends Entity> extends Deferred<EntityType<?>, EntityType<T>>
{
    public DeferredEntity(Supplier<Holder<EntityType<?>>> supplier)
    {
        super(supplier);
    }
}
