package dev.obscuria.fragmentum.service;

import dev.obscuria.fragmentum.network.PayloadRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public interface NetworkService
{
    PayloadRegistrar payloadRegistrar(String modId);

    <T> void reply(T payload);

    <T> void sendTo(ServerPlayer player, T payload);

    <T> void sendToTracking(ServerLevel level, BlockPos pos, T payload);

    <T> void sendToTracking(Entity entity, T payload);

    <T> void sendToAll(MinecraftServer server, T payload);

    <T> void sendToServer(T payload);
}
