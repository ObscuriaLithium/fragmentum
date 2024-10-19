package dev.obscuria.fragmentum.neoforge.service;

import dev.obscuria.fragmentum.api.v1.server.FragmentumServerRegistry;
import dev.obscuria.fragmentum.api.v1.server.V1Server;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public final class NeoV1Server implements V1Server
{
    @Override
    public void registerCommand(FragmentumServerRegistry.CommandRegistrar registrar)
    {
        NeoForge.EVENT_BUS.addListener((RegisterCommandsEvent event) ->
                registrar.register(
                        event.getDispatcher(),
                        event.getBuildContext(),
                        event.getCommandSelection()));
    }
}
