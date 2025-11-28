package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.registry.Registrar;

import java.nio.file.Path;
import java.util.Optional;

public interface FragmentumServices {

    Optional<Path> resolveRootPath(String modId);

    Registrar registrar(String modId);

    FactoryService factory();

    NetworkService network();

    ServerService server();

    ClientService client();

    ConfigService config();
}
