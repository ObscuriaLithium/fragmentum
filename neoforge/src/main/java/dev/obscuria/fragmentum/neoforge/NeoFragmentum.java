package dev.obscuria.fragmentum.neoforge;

import dev.obscuria.fragmentum.Fragmentum;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

@Mod(Fragmentum.MODID)
public final class NeoFragmentum {

    public NeoFragmentum(IEventBus eventBus) {
        Fragmentum.init();
    }

    public static <T extends Event> void addListener(String modId, Consumer<T> listener) {
        @Nullable var eventBus = ModLoadingContext.get().getActiveContainer().getEventBus();
        if (eventBus == null) throw new IllegalStateException("Invalid mod loading context:");
        eventBus.addListener(listener);
    }

    public static void register(String modId, DeferredRegister<?> register) {
        @Nullable var eventBus = ModLoadingContext.get().getActiveContainer().getEventBus();
        if (eventBus == null) throw new IllegalStateException("Invalid mod loading context:");
        register.register(eventBus);
    }
}