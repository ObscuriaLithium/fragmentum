package dev.obscuria.fragmentum.forge;

import dev.obscuria.fragmentum.api.Fragmentum;
import dev.obscuria.fragmentum.api.v1.server.FragmentumServerEvents;
import dev.obscuria.fragmentum.core.CoreFragmentum;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Consumer;

@ApiStatus.Internal
@Mod(Fragmentum.MODID)
public final class ForgeFragmentum
{
    public ForgeFragmentum()
    {
        CoreFragmentum.init();

        MinecraftForge.EVENT_BUS.addListener((final ServerStartingEvent event) -> FragmentumServerEvents.SERVER_STARTING
                .broadcast(listener -> listener.invoke(event.getServer())));
        MinecraftForge.EVENT_BUS.addListener((final ServerStartedEvent event) -> FragmentumServerEvents.SERVER_STARTED
                .broadcast(listener -> listener.invoke(event.getServer())));
        MinecraftForge.EVENT_BUS.addListener((final ServerStoppingEvent event) -> FragmentumServerEvents.SERVER_STOPPING
                .broadcast(listener -> listener.invoke(event.getServer())));
        MinecraftForge.EVENT_BUS.addListener((final ServerStoppedEvent event) -> FragmentumServerEvents.SERVER_STOPPED
                .broadcast(listener -> listener.invoke(event.getServer())));

        if (FMLEnvironment.dist.isClient())
            ForgeFragmentumClient.init();
    }

    public static <T extends Event> void addListener(String modId, Consumer<T> listener)
    {
        final var rawContainer = ModList.get().getModContainerById(modId).orElseThrow();
        if (!(rawContainer instanceof FMLModContainer container)) return;
        container.getEventBus().addListener(listener);
    }

    public static void register(String modId, DeferredRegister<?> register)
    {
        final var rawContainer = ModList.get().getModContainerById(modId).orElseThrow();
        if (!(rawContainer instanceof FMLModContainer container)) return;
        register.register(container.getEventBus());
    }
}