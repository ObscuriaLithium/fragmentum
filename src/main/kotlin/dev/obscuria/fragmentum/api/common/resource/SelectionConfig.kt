package dev.obscuria.fragmentum.api.common.resource

import net.minecraft.server.packs.repository.Pack

data class SelectionConfig(
	val required: Boolean,
	val defaultPosition: Pack.Position,
	val fixedPosition: Boolean
) {

	//? >=1.21.1 {
	/*fun asVanilla(): net.minecraft.server.packs.PackSelectionConfig {
		return net.minecraft.server.packs.PackSelectionConfig(required, defaultPosition, fixedPosition)
	}
	*///?}
}
