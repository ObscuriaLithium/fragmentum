package dev.obscuria.fragmentum.registry;

import net.minecraft.core.Holder;

@SuppressWarnings("unused")
@FunctionalInterface
public interface HolderProvider<T>
{
    Holder<T> holder();
}
