package dev.obscuria.fragmentum.api.registry

import net.minecraft.core.Holder

interface HolderProvider<T> {

    val holder: Holder<T>
}