package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.v2.api.client.ClientRegistrar;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface ClientService {

    ClientRegistrar registrar(String modId);
}
