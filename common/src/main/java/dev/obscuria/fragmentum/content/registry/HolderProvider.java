package dev.obscuria.fragmentum.content.registry;

import net.minecraft.core.Holder;

@FunctionalInterface
public interface HolderProvider<T> {

    Holder<T> holder();
}
