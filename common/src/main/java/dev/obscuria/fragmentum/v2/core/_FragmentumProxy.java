package dev.obscuria.fragmentum.v2.core;

import com.google.common.base.Preconditions;
import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.MinecraftServer;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unused")
public final class _FragmentumProxy {

    private static @Nullable MinecraftServer server = null;

    public static MinecraftServer server() {
        return Objects.requireNonNull(server);
    }

    public static Optional<MinecraftServer> optionalServer() {
        return Optional.ofNullable(server);
    }

    public static RegistryAccess registryAccess() {

        if (server != null) return server.registryAccess();
        Preconditions.checkState(Fragmentum.PLATFORM.isClient(), "Can't reach server RegistryAccess");

        final @Nullable var integratedServer = Minecraft.getInstance().getSingleplayerServer();
        if (integratedServer != null) return integratedServer.registryAccess();

        final @Nullable var connection = Minecraft.getInstance().getConnection();
        Preconditions.checkNotNull(connection, "Can't reach client RegistryAccess");
        return connection.registryAccess();
    }

    public static void onServerStart(MinecraftServer server) {
        _FragmentumProxy.server = server;
    }

    public static void onServerStop(MinecraftServer server) {
        _FragmentumProxy.server = null;
    }
}
