package dev.obscuria.fragmentum.v2.api.common.registry;

import net.minecraft.core.Holder;

@FunctionalInterface
public interface HolderProvider<T> {

    Holder<T> holder();
}
