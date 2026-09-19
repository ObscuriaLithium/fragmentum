package dev.obscuria.fragmentum.api.common.tags

import dev.obscuria.fragmentum.common.tags.DynamicTagEntryImpl
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagEntry

fun interface DynamicTagEntry {

	fun resolve(): TagEntry

	companion object {

		fun parse(input: String): DynamicTagEntry {
			return DynamicTagEntryImpl.parse(input)
		}

		fun element(id: ResourceLocation): DynamicTagEntry {
			return DynamicTagEntryImpl.element(id)
		}

		fun tag(id: ResourceLocation): DynamicTagEntry {
			return DynamicTagEntryImpl.tag(id)
		}
	}
}
