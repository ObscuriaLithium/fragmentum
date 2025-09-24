package dev.obscuria.fragmentum.registry;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class DeferredAttribute extends Deferred<Attribute, Attribute>
{
    public DeferredAttribute(Supplier<Holder<Attribute>> supplier)
    {
        super(supplier);
    }
}
