package dev.obscuria.fragmentum.v2.api;

import dev.obscuria.fragmentum.v2.core._FragmentumProxy;
import lombok.experimental.UtilityClass;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.MinecraftServer;

import java.util.Optional;

@UtilityClass
@SuppressWarnings("unused")
public final class FragmentumProxy {

    public static MinecraftServer server() {
        return _FragmentumProxy.server();
    }

    public static Optional<MinecraftServer> optionalServer() {
        return _FragmentumProxy.optionalServer();
    }

    public static RegistryAccess registryAccess() {
        return _FragmentumProxy.registryAccess();
    }
}
