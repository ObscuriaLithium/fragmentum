package dev.obscuria.fragmentum.forge;

import dev.obscuria.fragmentum.api.Fragmentum;
import dev.obscuria.fragmentum.api.v1.server.FragmentumServerEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Consumer;

@Mod(Fragmentum.MODID)
public final class ForgeFragmentum
{
    public ForgeFragmentum()
    {
        ForgeFragmentumClient.init();

        MinecraftForge.EVENT_BUS.addListener((final ServerStartingEvent event) ->
                FragmentumServerEvents.SERVER_STARTING.broadcast(listener ->
                        listener.invoke(event.getServer())));
        MinecraftForge.EVENT_BUS.addListener((final ServerStartedEvent event) ->
                FragmentumServerEvents.SERVER_STARTED.broadcast(listener ->
                        listener.invoke(event.getServer())));

        MinecraftForge.EVENT_BUS.addListener((final ServerStoppingEvent event) ->
                FragmentumServerEvents.SERVER_STOPPING.broadcast(listener ->
                        listener.invoke(event.getServer())));
        MinecraftForge.EVENT_BUS.addListener((final ServerStoppedEvent event) ->
                FragmentumServerEvents.SERVER_STOPPED.broadcast(listener ->
                        listener.invoke(event.getServer())));
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