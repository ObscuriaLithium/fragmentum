package dev.obscuria.fragmentum.registry

import java.util.function.Supplier

interface ValueProvider<T> : Supplier<T> {

    val value: T

    override fun get(): T = value
}