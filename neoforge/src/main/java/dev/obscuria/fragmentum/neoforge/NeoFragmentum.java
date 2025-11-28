package dev.obscuria.fragmentum.neoforge;

import dev.obscuria.fragmentum.Fragmentum;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;

@Mod(Fragmentum.MOD_ID)
public class NeoFragmentum {

    public NeoFragmentum(IEventBus eventBus) {
        Fragmentum.init();
    }

    public static <T extends Event> void addListener(String modId, Consumer<T> listener) {
        final var container = ModList.get().getModContainerById(modId).orElseThrow();
        if (!(container instanceof FMLModContainer mod)) return;
        assert mod.getEventBus() != null;
        mod.getEventBus().addListener(listener);
    }

    public static void register(String modId, DeferredRegister<?> register) {
        final var container = ModList.get().getModContainerById(modId).orElseThrow();
        if (!(container instanceof FMLModContainer mod)) return;
        assert mod.getEventBus() != null;
        register.register(mod.getEventBus());
    }
}