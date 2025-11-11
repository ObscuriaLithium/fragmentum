package dev.obscuria.fragmentum.server;

import dev.obscuria.fragmentum.util.signal.Signal1;
import net.minecraft.server.MinecraftServer;

public interface FragmentumServer {

    Signal1<MinecraftServer> SERVER_STARTING = null;
    Signal1<MinecraftServer> SERVER_SAVING = null;
    Signal1<MinecraftServer> SERVER_STOPPING = null;
}
