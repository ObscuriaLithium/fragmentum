package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.config.ConfigLayout;

import java.util.function.Consumer;

public interface ConfigService {

    <T extends ConfigLayout> void registerClient(String modId, T layout, Consumer<T> listener);

    <T extends ConfigLayout> void registerClient(String modId, String fileName, T layout, Consumer<T> listener);

    <T extends ConfigLayout> void registerCommon(String modId, T layout, Consumer<T> listener);

    <T extends ConfigLayout> void registerCommon(String modId, String fileName, T layout, Consumer<T> listener);

    <T extends ConfigLayout> void registerServer(String modId, T layout, Consumer<T> listener);

    <T extends ConfigLayout> void registerServer(String modId, String fileName, T layout, Consumer<T> listener);
}
