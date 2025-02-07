package dev.obscuria.fragmentum.api.registry

import net.minecraft.core.Holder
import net.minecraft.world.effect.MobEffect
import java.util.function.Supplier

class DeferredMobEffect<T : MobEffect>(supplier: Supplier<Holder<MobEffect>>)
    : Deferred<MobEffect, T>(supplier)