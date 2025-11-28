package dev.obscuria.fragmentum.config;

import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Consumer;

@Deprecated
@SuppressWarnings("unused")
public interface ConfigRegistry {

    static <T extends ConfigLayout> void registerClient(String modId, T layout, Consumer<T> listener) {
        throw new NotImplementedException();
    }

    static <T extends ConfigLayout> void registerClient(String modId, String fileName, T layout, Consumer<T> listener) {
        throw new NotImplementedException();
    }

    static <T extends ConfigLayout> void registerCommon(String modId, T layout, Consumer<T> listener) {
        throw new NotImplementedException();
    }

    static <T extends ConfigLayout> void registerCommon(String modId, String fileName, T layout, Consumer<T> listener) {
        throw new NotImplementedException();
    }

    static <T extends ConfigLayout> void registerServer(String modId, T layout, Consumer<T> listener) {
        throw new NotImplementedException();
    }

    static <T extends ConfigLayout> void registerServer(String modId, String fileName, T layout, Consumer<T> listener) {
        throw new NotImplementedException();
    }
}
