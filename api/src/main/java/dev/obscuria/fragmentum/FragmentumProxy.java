package dev.obscuria.fragmentum;

import net.minecraft.core.RegistryAccess;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Optional;

@SuppressWarnings("all")
public final class FragmentumProxy {

    public static MinecraftServer server() {
        throw new NotImplementedException();
    }

    public static Optional<MinecraftServer> optionalServer() {
        throw new NotImplementedException();
    }

    public static RegistryAccess registryAccess() {
        throw new NotImplementedException();
    }
}
