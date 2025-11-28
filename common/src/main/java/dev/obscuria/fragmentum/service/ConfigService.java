package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.config.ConfigBuilder;

public interface ConfigService {

    void registerClient(String modId, ConfigBuilder builder);

    void registerCommon(String modId, ConfigBuilder builder);

    void registerServer(String modId, ConfigBuilder builder);
}
