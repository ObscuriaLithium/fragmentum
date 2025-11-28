package dev.obscuria.fragmentum.content.network;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

@SuppressWarnings("unused")
public interface FragmentumNetworking {

    static PayloadRegistrar registrar(String modId) {
        return Fragmentum.SERVICES.network().payloadRegistrar(modId);
    }

    static <T extends CustomPacketPayload> void reply(T payload) {
        Fragmentum.SERVICES.network().reply(payload);
    }

    static <T extends CustomPacketPayload> void sendTo(ServerPlayer player, T payload) {
        Fragmentum.SERVICES.network().sendTo(player, payload);
    }

    static <T extends CustomPacketPayload> void sendToAllTracking(ServerLevel level, BlockPos pos, T payload) {
        Fragmentum.SERVICES.network().sendToAllTracking(level, pos, payload);
    }

    static <T extends CustomPacketPayload> void sendToAllTracking(Entity entity, T payload) {
        Fragmentum.SERVICES.network().sendToAllTracking(entity, payload);
    }

    static <T extends CustomPacketPayload> void sendToAll(MinecraftServer server, T payload) {
        Fragmentum.SERVICES.network().sendToAll(server, payload);
    }

    static <T extends CustomPacketPayload> void sendToServer(T payload) {
        Fragmentum.SERVICES.network().sendToServer(payload);
    }
}
