package dev.obscuria.fragmentum.api.common.tags

fun interface TagPostProcessor {

	fun collect(): Collection<DynamicTagEntry>
}
