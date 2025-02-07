package dev.obscuria.fragmentum.api.tools.event

fun interface EventHandler<T>
{
    fun handle(listener: T)
}