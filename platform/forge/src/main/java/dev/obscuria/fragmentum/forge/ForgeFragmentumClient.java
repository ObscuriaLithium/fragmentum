package dev.obscuria.fragmentum.forge;

import dev.obscuria.fragmentum.api.v1.client.FragmentumClientEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;

public final class ForgeFragmentumClient
{
    public static void init()
    {
        if (!FMLEnvironment.dist.isClient()) return;

        MinecraftForge.EVENT_BUS.addListener((final RenderLevelStageEvent event) ->
        {
            if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY)
                FragmentumClientEvents.START_RENDER.broadcast(FragmentumClientEvents.WorldRender::invoke);
            if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_LEVEL)
                FragmentumClientEvents.END_RENDER.broadcast(FragmentumClientEvents.WorldRender::invoke);
        });

        MinecraftForge.EVENT_BUS.addListener((final TickEvent.ClientTickEvent.Pre event) ->
                FragmentumClientEvents.START_CLIENT_TICK.broadcast(listener ->
                        listener.invoke(Minecraft.getInstance())));
        MinecraftForge.EVENT_BUS.addListener((final TickEvent.ClientTickEvent.Post event) ->
                FragmentumClientEvents.END_CLIENT_TICK.broadcast(listener ->
                        listener.invoke(Minecraft.getInstance())));

        MinecraftForge.EVENT_BUS.addListener((final TickEvent.LevelTickEvent.Pre event) ->
                FragmentumClientEvents.START_WORLD_TICK.broadcast(listener ->
                        listener.invoke((ClientLevel) event.level)));
        MinecraftForge.EVENT_BUS.addListener((final TickEvent.LevelTickEvent.Post event) ->
                FragmentumClientEvents.END_WORLD_TICK.broadcast(listener ->
                        listener.invoke((ClientLevel) event.level)));
    }
}