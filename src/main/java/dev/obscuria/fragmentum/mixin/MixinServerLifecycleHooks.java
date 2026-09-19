package dev.obscuria.fragmentum.mixin;

//? forge || neoforge {
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import dev.obscuria.fragmentum.FragmentumContextImpl;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

//? forge {
import net.minecraftforge.server.ServerLifecycleHooks;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//?}

//? neoforge {
/*import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*///?}

@Mixin(value = ServerLifecycleHooks.class, remap = false)
@MixinEnvironment(type = MixinEnvironment.Env.DEFAULT)
public abstract class MixinServerLifecycleHooks {

	@Inject(method = "handleServerAboutToStart", at = @At("HEAD"), remap = false)
	private static void handleServerAboutToStart$onHead(
			MinecraftServer server,
			/*? forge {*/ CallbackInfoReturnable<Boolean> cir /*?}*/
			/*? neoforge {*/ /*CallbackInfo ci *//*?}*/
	) {
		FragmentumContextImpl.INSTANCE.notifyServerStarting(server);
	}
}
//?}
