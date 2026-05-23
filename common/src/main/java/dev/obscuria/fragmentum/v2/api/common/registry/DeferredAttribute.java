package dev.obscuria.fragmentum.v2.api.common.registry;

import dev.obscuria.fragmentum.v2.core.common.registry._DeferredAttribute;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;

@SuppressWarnings("unused")
public interface DeferredAttribute extends Deferred<Attribute> {

    static DeferredAttribute create(Holder<Attribute> holder) {
        return new _DeferredAttribute(holder);
    }
}
