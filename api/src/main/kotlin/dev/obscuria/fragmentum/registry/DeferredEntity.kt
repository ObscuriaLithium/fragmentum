@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.registry

import net.minecraft.core.Holder
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType

class DeferredEntity<T : Entity>(supplier: () -> Holder<EntityType<*>>) :
    Deferred<EntityType<*>, EntityType<T>>(supplier)