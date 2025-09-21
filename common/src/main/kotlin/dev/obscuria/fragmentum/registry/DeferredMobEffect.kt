package dev.obscuria.fragmentum.registry

import net.minecraft.core.Holder
import net.minecraft.world.effect.MobEffect

class DeferredMobEffect<T : MobEffect>(
    supplier: () -> Holder<MobEffect>
) : Deferred<MobEffect, T>(supplier)