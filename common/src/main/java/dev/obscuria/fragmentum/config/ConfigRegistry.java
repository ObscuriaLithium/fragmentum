package dev.obscuria.fragmentum.config;

import dev.obscuria.fragmentum.Fragmentum;

import java.util.function.Consumer;

@Deprecated
@SuppressWarnings("unused")
public interface ConfigRegistry {

    static <T extends ConfigLayout> void registerClient(String modId, T layout, Consumer<T> listener) {
        Fragmentum.SERVICES.config().registerClient(modId, layout, listener);
    }

    static <T extends ConfigLayout> void registerClient(String modId, String fileName, T layout, Consumer<T> listener) {
        Fragmentum.SERVICES.config().registerClient(modId, fileName, layout, listener);
    }

    static <T extends ConfigLayout> void registerCommon(String modId, T layout, Consumer<T> listener) {
        Fragmentum.SERVICES.config().registerCommon(modId, layout, listener);
    }

    static <T extends ConfigLayout> void registerCommon(String modId, String fileName, T layout, Consumer<T> listener) {
        Fragmentum.SERVICES.config().registerCommon(modId, fileName, layout, listener);
    }

    static <T extends ConfigLayout> void registerServer(String modId, T layout, Consumer<T> listener) {
        Fragmentum.SERVICES.config().registerServer(modId, layout, listener);
    }

    static <T extends ConfigLayout> void registerServer(String modId, String fileName, T layout, Consumer<T> listener) {
        Fragmentum.SERVICES.config().registerServer(modId, fileName, layout, listener);
    }
}
