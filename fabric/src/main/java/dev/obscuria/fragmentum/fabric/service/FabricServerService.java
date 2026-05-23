package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.service.ServerService;
import dev.obscuria.fragmentum.v2.api.server.FragmentumServerRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public final class FabricServerService implements ServerService {

    public static final FabricServerService SHARED = new FabricServerService();

    @Override
    public void registerCommand(FragmentumServerRegistry.CommandRegistrar registrar) {
        CommandRegistrationCallback.EVENT.register(registrar::register);
    }

    private FabricServerService() {}
}
