package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.v2.api.server.FragmentumServerRegistry;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface ServerService {

    void registerCommand(FragmentumServerRegistry.CommandRegistrar registrar);
}
