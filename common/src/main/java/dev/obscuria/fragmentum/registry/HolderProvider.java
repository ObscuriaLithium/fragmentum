package dev.obscuria.fragmentum.registry;

import net.minecraft.core.Holder;

@FunctionalInterface
public interface HolderProvider<T>
{
    Holder<T> holder();
}
