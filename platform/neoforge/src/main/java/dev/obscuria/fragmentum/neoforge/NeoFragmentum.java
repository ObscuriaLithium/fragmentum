package dev.obscuria.fragmentum.neoforge;

import dev.obscuria.fragmentum.api.Fragmentum;
import dev.obscuria.fragmentum.api.v1.server.FragmentumServerEvents;
import dev.obscuria.fragmentum.core.CoreFragmentum;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import org.jetbrains.annotations.ApiStatus;

import java.util.Objects;

@ApiStatus.Internal
@Mod(Fragmentum.MODID)
public final class NeoFragmentum
{
    public NeoFragmentum(IEventBus eventBus)
    {
        CoreFragmentum.init();

        NeoForge.EVENT_BUS.addListener((final ServerStartingEvent event) -> FragmentumServerEvents.SERVER_STARTING
                .broadcast(listener -> listener.invoke(event.getServer())));
        NeoForge.EVENT_BUS.addListener((final ServerStartedEvent event) -> FragmentumServerEvents.SERVER_STARTED
                .broadcast(listener -> listener.invoke(event.getServer())));
        NeoForge.EVENT_BUS.addListener((final ServerStoppingEvent event) -> FragmentumServerEvents.SERVER_STOPPING
                .broadcast(listener -> listener.invoke(event.getServer())));
        NeoForge.EVENT_BUS.addListener((final ServerStoppedEvent event) -> FragmentumServerEvents.SERVER_STOPPED
                .broadcast(listener -> listener.invoke(event.getServer())));
    }

    public static IEventBus eventBus(String modId)
    {
        return Objects.requireNonNull(ModList.get().getModContainerById(modId).orElseThrow().getEventBus());
    }
}