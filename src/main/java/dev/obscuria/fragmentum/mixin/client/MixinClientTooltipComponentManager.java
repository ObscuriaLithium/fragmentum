package dev.obscuria.fragmentum.mixin.client;

//? forge || neoforge {
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.obscuria.fragmentum.client.TooltipComponentRegistry;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//? forge
import net.minecraftforge.client.gui.ClientTooltipComponentManager;
//? neoforge
//import net.neoforged.neoforge.client.gui.ClientTooltipComponentManager;

@Mixin(value = ClientTooltipComponentManager.class, remap = false)
@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
public abstract class MixinClientTooltipComponentManager {

    @Inject(method = "createClientTooltipComponent", at = @At("RETURN"), cancellable = true)
    private static void createCustomComponent(TooltipComponent component, CallbackInfoReturnable<ClientTooltipComponent> info) {
        @Nullable var clientComponent = TooltipComponentRegistry.INSTANCE.create(component);
    	if (clientComponent != null) info.setReturnValue(clientComponent);
	}
}
//?}
