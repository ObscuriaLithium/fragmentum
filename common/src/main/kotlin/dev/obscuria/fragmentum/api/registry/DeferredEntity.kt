package dev.obscuria.fragmentum.api.registry

import net.minecraft.core.Holder
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import java.util.function.Supplier

class DeferredEntity<T : Entity>(supplier: Supplier<Holder<EntityType<*>>>)
    : Deferred<EntityType<*>, EntityType<T>>(supplier)