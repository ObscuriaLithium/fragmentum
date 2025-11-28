package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.server.FragmentumServerRegistry;

public interface ServerService {

    void registerCommand(FragmentumServerRegistry.CommandRegistrar registrar);
}
