package dev.obscuria.fragmentum.forge.service;

import dev.obscuria.fragmentum.api.v1.server.FragmentumServerRegistry;
import dev.obscuria.fragmentum.api.v1.server.V1Server;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class ForgeV1Server implements V1Server
{
    @Override
    public void registerCommand(FragmentumServerRegistry.CommandRegistrar registrar)
    {
        MinecraftForge.EVENT_BUS.addListener((RegisterCommandsEvent event) -> registrar.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection()));
    }
}
