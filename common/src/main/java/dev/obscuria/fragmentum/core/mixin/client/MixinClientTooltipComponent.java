package dev.obscuria.fragmentum.core.mixin.client;

import dev.obscuria.fragmentum.core.v1.client.TooltipComponentFactories;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientTooltipComponent.class)
public interface MixinClientTooltipComponent
{
    @Inject(method = "create(Lnet/minecraft/world/inventory/tooltip/TooltipComponent;)Lnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipComponent;", at = @At("HEAD"), cancellable = true)
    private static void create_modify(TooltipComponent component, CallbackInfoReturnable<ClientTooltipComponent> info)
    {
        TooltipComponentFactories.create(component).ifPresent(info::setReturnValue);
    }
}
