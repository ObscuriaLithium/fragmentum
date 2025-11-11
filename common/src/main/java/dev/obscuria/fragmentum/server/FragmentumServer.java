package dev.obscuria.fragmentum.server;

import dev.obscuria.fragmentum.util.signal.Signal1;
import net.minecraft.server.MinecraftServer;

public interface FragmentumServer {

    Signal1<MinecraftServer> SERVER_STARTING = new Signal1<>();
    Signal1<MinecraftServer> SERVER_SAVING = new Signal1<>();
    Signal1<MinecraftServer> SERVER_STOPPING = new Signal1<>();
}
