package dev.obscuria.fragmentum.v2.core.common.registry;

import dev.obscuria.fragmentum.v2.api.common.registry.DeferredAttribute;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;

public final class _DeferredAttribute extends _Deferred<Attribute> implements DeferredAttribute {

    public _DeferredAttribute(Holder<Attribute> holder) {
        super(holder);
    }
}
