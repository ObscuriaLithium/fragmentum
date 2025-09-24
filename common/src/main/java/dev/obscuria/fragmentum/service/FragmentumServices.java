package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.registry.Registrar;

public interface FragmentumServices
{
    Registrar registrar(String modId);

    FactoryService factory();

    NetworkService network();

    ServerService server();

    ClientService client();
}
