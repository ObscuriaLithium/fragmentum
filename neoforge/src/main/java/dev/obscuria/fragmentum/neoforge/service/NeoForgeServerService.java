package dev.obscuria.fragmentum.neoforge.service;

import dev.obscuria.fragmentum.service.ServerService;
import dev.obscuria.fragmentum.v2.api.server.FragmentumServerRegistry;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public final class NeoForgeServerService implements ServerService {

    public static final NeoForgeServerService SHARED = new NeoForgeServerService();

    @Override
    public void registerCommand(FragmentumServerRegistry.CommandRegistrar registrar) {
        NeoForge.EVENT_BUS.addListener((RegisterCommandsEvent event) -> registrar.register(
                event.getDispatcher(),
                event.getBuildContext(),
                event.getCommandSelection()));
    }

    private NeoForgeServerService() {}
}
