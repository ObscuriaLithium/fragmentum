package dev.obscuria.fragmentum.tools.event

fun interface EventHandler<T> {

    fun handle(listener: T)
}