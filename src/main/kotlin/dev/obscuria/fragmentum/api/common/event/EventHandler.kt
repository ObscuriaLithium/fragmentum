package dev.obscuria.fragmentum.api.common.event

fun interface EventHandler<T> {

	fun handle(listener: T)
}
