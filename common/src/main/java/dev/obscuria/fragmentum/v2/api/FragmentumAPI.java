package dev.obscuria.fragmentum.v2.api;

import dev.obscuria.fragmentum.Fragmentum;
import dev.obscuria.fragmentum.Platform;

@SuppressWarnings("unused")
public final class FragmentumAPI {

    public static String modId() {
        return Fragmentum.MOD_ID;
    }

    public static String modName() {
        return Fragmentum.MOD_NAME;
    }

    public static Platform platform() {
        return Fragmentum.PLATFORM;
    }
}