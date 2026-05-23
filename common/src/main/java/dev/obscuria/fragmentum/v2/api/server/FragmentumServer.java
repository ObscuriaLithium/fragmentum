package dev.obscuria.fragmentum.v2.api.server;

import dev.obscuria.fragmentum.v2.api.common.signal.Signal1;
import net.minecraft.server.MinecraftServer;

@SuppressWarnings("unused")
public final class FragmentumServer {

    public static final Signal1<MinecraftServer> SERVER_STARTING = Signal1.create();
    public static final Signal1<MinecraftServer> SERVER_SAVING = Signal1.create();
    public static final Signal1<MinecraftServer> SERVER_STOPPING = Signal1.create();
}
