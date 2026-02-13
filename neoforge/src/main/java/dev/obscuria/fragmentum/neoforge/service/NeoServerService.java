package dev.obscuria.fragmentum.neoforge.service;

import dev.obscuria.fragmentum.server.FragmentumServerRegistry;
import dev.obscuria.fragmentum.service.ServerService;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public final class NeoServerService implements ServerService {

    public static final NeoServerService INSTANCE = new NeoServerService();

    private NeoServerService() {}

    @Override
    public void registerCommand(FragmentumServerRegistry.CommandRegistrar registrar) {
        NeoForge.EVENT_BUS.addListener((RegisterCommandsEvent event) -> registrar.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection()));
    }
}
