package dev.obscuria.fragmentum.api.registry

import java.util.function.Supplier

interface ValueProvider<T> : Supplier<T> {

    val value: T

    override fun get(): T = value
}