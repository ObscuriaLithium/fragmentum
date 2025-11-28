package dev.obscuria.fragmentum.content.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("unused")
public interface FragmentumNetworking {

    static PayloadRegistrar registrar(String modId) {
        throw new NotImplementedException();
    }

    static <T extends CustomPacketPayload> void reply(T payload) {
        throw new NotImplementedException();
    }

    static <T extends CustomPacketPayload> void sendTo(ServerPlayer player, T payload) {
        throw new NotImplementedException();
    }

    static <T extends CustomPacketPayload> void sendToAllTracking(ServerLevel level, BlockPos pos, T payload) {
        throw new NotImplementedException();
    }

    static <T extends CustomPacketPayload> void sendToAllTracking(Entity entity, T payload) {
        throw new NotImplementedException();
    }

    static <T extends CustomPacketPayload> void sendToAll(MinecraftServer server, T payload) {
        throw new NotImplementedException();
    }

    static <T extends CustomPacketPayload> void sendToServer(T payload) {
        throw new NotImplementedException();
    }
}
