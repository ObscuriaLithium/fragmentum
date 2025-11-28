package dev.obscuria.fragmentum.content.registry;

import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("unused")
public interface FragmentumRegistry {

    static Registrar registrar(String modId) {
        throw new NotImplementedException();
    }
}