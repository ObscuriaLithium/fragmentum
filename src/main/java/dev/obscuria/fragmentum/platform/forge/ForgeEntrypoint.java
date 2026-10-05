package dev.obscuria.fragmentum.platform.forge;

//? forge {
import dev.obscuria.fragmentum.Fragmentum;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Consumer;

@Mod(Fragmentum.MOD_ID)
public class ForgeEntrypoint {

	public ForgeEntrypoint() {
		Fragmentum.INSTANCE.onInitialize();
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ForgeClientEntrypoint::init);
	}

	public static <T extends Event> void addListener(String modId, Consumer<T> listener) {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(listener);
	}

	public static void register(String modId, DeferredRegister<?> register) {
		register.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
//?}
