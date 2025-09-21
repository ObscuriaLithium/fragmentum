@file:Suppress("unused", "RedundantNullableReturnType")

package dev.obscuria.fragmentum.registry

import net.minecraft.core.Holder
import net.minecraft.world.entity.ai.attributes.Attribute

class DeferredAttribute(supplier: () -> Holder<Attribute>) : Deferred<Attribute, Attribute>(supplier)