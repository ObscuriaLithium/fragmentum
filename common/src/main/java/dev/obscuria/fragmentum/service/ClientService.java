package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.v2.api.client.ClientRegistrar;

public interface ClientService {

    ClientRegistrar registrar(String modId);
}
