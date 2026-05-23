package dev.obscuria.fragmentum.v2.core.common.registry;

import dev.obscuria.fragmentum.v2.api.common.registry.DeferredEntity;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public final class _DeferredEntity<T extends Entity> extends _Deferred<EntityType<T>> implements DeferredEntity<T> {

    public _DeferredEntity(Holder<EntityType<T>> holder) {
        super(holder);
    }
}