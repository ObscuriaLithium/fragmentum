package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.content.registry.Registrar;

public interface FragmentumServices {

    Registrar registrar(String modId);

    FactoryService factory();

    NetworkService network();

    ServerService server();

    ClientService client();

    ConfigService config();
}
