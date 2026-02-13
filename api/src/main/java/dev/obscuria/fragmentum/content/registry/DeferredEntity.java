package dev.obscuria.fragmentum.content.registry;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class DeferredEntity<T extends Entity> extends Deferred<EntityType<T>> {

    public DeferredEntity(Holder<EntityType<T>> holder) {
        super(holder);
    }
}