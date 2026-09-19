package dev.obscuria.fragmentum.common.tags

import dev.obscuria.fragmentum.Fragmentum
import dev.obscuria.fragmentum.api.common.tags.DynamicTagEntry
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagEntry
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
internal object DynamicTagEntryImpl {

	fun parse(input: String): DynamicTagEntry = runCatching {
		when {
			input.startsWith("#") -> tag(Fragmentum.parseId(input.drop(1)))
			else -> element(Fragmentum.parseId(input))
		}
	}.getOrElse {
		element(Fragmentum.parseId("placeholder"))
	}

	fun element(id: ResourceLocation) = DynamicTagEntry {
		TagEntry.optionalElement(id)
	}

	fun tag(id: ResourceLocation) = DynamicTagEntry {
		TagEntry.optionalTag(id)
	}
}
