package dev.obscuria.fragmentum;

import com.google.common.base.Preconditions;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class FragmentumProxy {

    private static @Nullable MinecraftServer server = null;

    public static MinecraftServer server() {
        return Objects.requireNonNull(server);
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
        FragmentumProxy.server = server;
    }

    public static void onServerStop(MinecraftServer server) {
        FragmentumProxy.server = null;
    }
}
