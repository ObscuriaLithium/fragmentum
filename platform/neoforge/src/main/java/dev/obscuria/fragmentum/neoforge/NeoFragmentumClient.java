package dev.obscuria.fragmentum.neoforge;

import dev.obscuria.fragmentum.api.Fragmentum;
import dev.obscuria.fragmentum.api.v1.client.FragmentumClientEvents;
import dev.obscuria.fragmentum.core.CoreFragmentumClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Mod(value = Fragmentum.MODID, dist = Dist.CLIENT)
public final class NeoFragmentumClient
{
    public NeoFragmentumClient()
    {
        CoreFragmentumClient.init();

        NeoForge.EVENT_BUS.addListener((final RenderLevelStageEvent event) ->
        {
            if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY)
                FragmentumClientEvents.START_RENDER.broadcast(FragmentumClientEvents.WorldRender::invoke);
            if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_LEVEL)
                FragmentumClientEvents.END_RENDER.broadcast(FragmentumClientEvents.WorldRender::invoke);
        });

        NeoForge.EVENT_BUS.addListener((final ClientTickEvent.Pre event) -> FragmentumClientEvents.START_CLIENT_TICK
                .broadcast(listener -> listener.invoke(Minecraft.getInstance())));
        NeoForge.EVENT_BUS.addListener((final ClientTickEvent.Post event) -> FragmentumClientEvents.END_CLIENT_TICK
                .broadcast(listener -> listener.invoke(Minecraft.getInstance())));
        NeoForge.EVENT_BUS.addListener((final LevelTickEvent.Pre event) -> FragmentumClientEvents.START_WORLD_TICK
                .broadcast(listener -> listener.invoke((ClientLevel) event.getLevel())));
        NeoForge.EVENT_BUS.addListener((final LevelTickEvent.Post event) -> FragmentumClientEvents.END_WORLD_TICK
                .broadcast(listener -> listener.invoke((ClientLevel) event.getLevel())));
    }
}