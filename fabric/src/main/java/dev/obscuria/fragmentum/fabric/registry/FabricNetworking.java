package dev.obscuria.fragmentum.fabric.registry;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import org.jetbrains.annotations.Nullable;

public final class FabricNetworking {

    public static @Nullable PacketSender clientReplySender;
    public static @Nullable PacketSender serverReplySender;
}
