package dev.obscuria.fragmentum.v2.api.common.registry;

import dev.obscuria.fragmentum.Fragmentum;

@SuppressWarnings("unused")
public final class FragmentumRegistry {

    static Registrar registrar(String modId) {
        return Fragmentum.SERVICES.registrar(modId);
    }
}