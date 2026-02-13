package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.content.network.PayloadRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public interface NetworkService {

    PayloadRegistrar payloadRegistrar(String modId);

    <T extends CustomPacketPayload> void reply(T payload);

    <T extends CustomPacketPayload> void sendTo(ServerPlayer player, T payload);

    <T extends CustomPacketPayload> void sendToAllTracking(ServerLevel level, BlockPos pos, T payload);

    <T extends CustomPacketPayload> void sendToAllTracking(Entity entity, T payload);

    <T extends CustomPacketPayload> void sendToAll(MinecraftServer server, T payload);

    <T extends CustomPacketPayload> void sendToServer(T payload);
}
