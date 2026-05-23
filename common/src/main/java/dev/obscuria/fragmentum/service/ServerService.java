package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.v2.api.server.FragmentumServerRegistry;

public interface ServerService {

    void registerCommand(FragmentumServerRegistry.CommandRegistrar registrar);
}
