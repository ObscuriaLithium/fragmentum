package dev.obscuria.fragmentum.packs;

import net.minecraft.server.packs.repository.Pack;

public record PackSelectionConfig(boolean required, Pack.Position defaultPosition, boolean fixedPosition) {}