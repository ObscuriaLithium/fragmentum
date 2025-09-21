package dev.obscuria.fragmentum.registry

import net.minecraft.core.Holder

interface HolderProvider<T> {

    val holder: Holder<T>
}