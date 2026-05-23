package dev.obscuria.fragmentum.v2.api.client;

import dev.obscuria.fragmentum.Fragmentum;

@SuppressWarnings("unused")
public final class FragmentumClientRegistry {

    public static ClientRegistrar registrar(String modId) {
        return Fragmentum.SERVICES.client().registrar(modId);
    }
}
