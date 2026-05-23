package dev.obscuria.fragmentum.v2.api.common.registry;

import dev.obscuria.fragmentum.v2.core.common.registry._DeferredEntity;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

@SuppressWarnings("unused")
public interface DeferredEntity<T extends Entity> extends Deferred<EntityType<T>> {

    static <T extends Entity> DeferredEntity<T> create(Holder<EntityType<T>> holder) {
        return new _DeferredEntity<>(holder);
    }
}