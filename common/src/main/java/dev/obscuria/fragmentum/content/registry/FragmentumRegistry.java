package dev.obscuria.fragmentum.content.registry;

import dev.obscuria.fragmentum.Fragmentum;

@SuppressWarnings("unused")
public interface FragmentumRegistry {

    static Registrar registrar(String modId) {
        return Fragmentum.SERVICES.registrar(modId);
    }
}