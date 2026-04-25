package dev.obscuria.fragmentum.forge;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Consumer;

@Mod(Fragmentum.MOD_ID)
public class ForgeFragmentum {

    public ForgeFragmentum() {
        Fragmentum.init();
    }

    public static <T extends Event> void addListener(String modId, Consumer<T> listener) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(listener);
        //final var container = ModList.get().getModContainerById(modId).orElseThrow();
        //if (!(container instanceof FMLModContainer mod)) return;
        //mod.getEventBus().addListener(listener);
    }

    public static void register(String modId, DeferredRegister<?> register) {
        register.register(FMLJavaModLoadingContext.get().getModEventBus());
        //final var container = ModList.get().getModContainerById(modId).orElseThrow();
        //if (!(container instanceof FMLModContainer mod)) return;
        //register.register(mod.getEventBus());
    }
}