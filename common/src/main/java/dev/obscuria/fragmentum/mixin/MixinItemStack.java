package dev.obscuria.fragmentum.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {

    @Inject(method = "getTooltipLines", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/Item;appendHoverText(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Ljava/util/List;Lnet/minecraft/world/item/TooltipFlag;)V"))
    private void modifyTooltipLines(
            Player player, TooltipFlag flag,
            CallbackInfoReturnable<List<Component>> info,
            @Local List<Component> tooltip) {

        Fragmentum.INSTANCE.modifyTooltip((ItemStack) (Object) this, tooltip::add);
    }

    @Inject(method = "getTooltipImage", at = @At("RETURN"), cancellable = true)
    private void modifyTooltipImages(CallbackInfoReturnable<Optional<TooltipComponent>> info) {

        var stack = (ItemStack) (Object) this;
        info.setReturnValue(Fragmentum.INSTANCE.gatherTooltipImages(stack, info.getReturnValue()));
    }
}
