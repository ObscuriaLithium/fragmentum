package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.v2.api.common.registry.Registrar;

public interface FragmentumServices {

    Registrar registrar(String modId);

    FactoryService factory();

    NetworkService network();

    ServerService server();

    ClientService client();

    ConfigService config();
}
