package dev.obscuria.fragmentum.forge.service;

import dev.obscuria.fragmentum.api.v1.server.ObscureServerRegistry;
import dev.obscuria.fragmentum.api.v1.server.V1Server;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;

public final class ForgeV1Server implements V1Server
{
    @Override
    public void registerCommand(ObscureServerRegistry.CommandRegistrar registrar)
    {
        MinecraftForge.EVENT_BUS.addListener((RegisterCommandsEvent event) ->
                registrar.register(
                        event.getDispatcher(),
                        event.getBuildContext(),
                        event.getCommandSelection()));
    }
}
