package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.v2.api.common.registry.Registrar;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface FragmentumServices {

    Registrar registrar(String modId);

    FactoryService factory();

    NetworkService network();

    ServerService server();

    ClientService client();

    ConfigService config();
}
