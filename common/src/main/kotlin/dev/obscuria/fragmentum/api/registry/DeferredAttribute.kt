package dev.obscuria.fragmentum.api.registry

import net.minecraft.core.Holder
import net.minecraft.world.entity.ai.attributes.Attribute
import java.util.function.Supplier

class DeferredAttribute(supplier: Supplier<Holder<Attribute>>)
    : Deferred<Attribute, Attribute>(supplier)