package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.server.FragmentumServerRegistry;
import dev.obscuria.fragmentum.service.ServerService;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public final class FabricServerService implements ServerService
{
    public static final FabricServerService INSTANCE = new FabricServerService();

    private FabricServerService() {}

    @Override
    public void registerCommand(FragmentumServerRegistry.CommandRegistrar registrar)
    {
        CommandRegistrationCallback.EVENT.register(registrar::register);
    }
}
