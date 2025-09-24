package dev.obscuria.fragmentum.forge.service;

import dev.obscuria.fragmentum.server.FragmentumServerRegistry;
import dev.obscuria.fragmentum.service.ServerService;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;

public final class ForgeServerService implements ServerService
{
    public static final ForgeServerService INSTANCE = new ForgeServerService();

    private ForgeServerService() {}

    @Override
    public void registerCommand(FragmentumServerRegistry.CommandRegistrar registrar)
    {
        MinecraftForge.EVENT_BUS.addListener((RegisterCommandsEvent event) -> registrar.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection()));
    }
}
