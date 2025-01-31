package dev.obscuria.fragmentum.core.v1.common;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

@ApiStatus.Internal
public record AllOfTooltipComponent(List<TooltipComponent> components) implements TooltipComponent {}
