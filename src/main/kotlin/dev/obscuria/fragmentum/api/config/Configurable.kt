package dev.obscuria.fragmentum.api.config

interface Configurable<T> where T : Any {

	val name: String

	val default: T

	fun get(): T

	fun set(value: T)

	fun save()
}
