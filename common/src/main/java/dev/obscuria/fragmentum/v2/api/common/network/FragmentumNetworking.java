package dev.obscuria.fragmentum.v2.api.common.network;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

@SuppressWarnings("unused")
public final class FragmentumNetworking {

    public static PayloadRegistrar registrar(String modId) {
        return Fragmentum.SERVICES.network().registrar(modId);
    }

    public static <T extends CustomPacketPayload> void reply(T payload) {
        Fragmentum.SERVICES.network().reply(payload);
    }

    public static <T extends CustomPacketPayload> void sendTo(ServerPlayer player, T payload) {
        Fragmentum.SERVICES.network().sendTo(player, payload);
    }

    public static <T extends CustomPacketPayload> void sendToAllTracking(ServerLevel level, BlockPos pos, T payload) {
        Fragmentum.SERVICES.network().sendToAllTracking(level, pos, payload);
    }

    public static <T extends CustomPacketPayload> void sendToAllTracking(Entity entity, T payload) {
        Fragmentum.SERVICES.network().sendToAllTracking(entity, payload);
    }

    public static <T extends CustomPacketPayload> void sendToAll(MinecraftServer server, T payload) {
        Fragmentum.SERVICES.network().sendToAll(server, payload);
    }

    public static <T extends CustomPacketPayload> void sendToServer(T payload) {
        Fragmentum.SERVICES.network().sendToServer(payload);
    }
}
