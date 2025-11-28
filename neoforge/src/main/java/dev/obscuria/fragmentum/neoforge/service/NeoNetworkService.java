package dev.obscuria.fragmentum.neoforge.service;

import dev.obscuria.fragmentum.content.network.PayloadRegistrar;
import dev.obscuria.fragmentum.neoforge.registry.NeoNetworking;
import dev.obscuria.fragmentum.neoforge.registry.NeoPayloadRegistrar;
import dev.obscuria.fragmentum.service.NetworkService;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.neoforged.neoforge.network.PacketDistributor;

public final class NeoNetworkService implements NetworkService {

    public static final NeoNetworkService INSTANCE = new NeoNetworkService();

    private NeoNetworkService() {}

    @Override
    public PayloadRegistrar payloadRegistrar(String modId) {
        return new NeoPayloadRegistrar(modId);
    }

    @Override
    public <T extends CustomPacketPayload> void reply(T payload) {
        if (NeoNetworking.replyConsumer == null) throw new IllegalStateException("No context to reply to");
        NeoNetworking.replyConsumer.accept(payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendTo(ServerPlayer player, T payload) {
        PacketDistributor.sendToPlayer(player, payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToAllTracking(ServerLevel level, BlockPos pos, T payload) {
        PacketDistributor.sendToPlayersTrackingChunk(level, new ChunkPos(pos), payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToAllTracking(Entity entity, T payload) {
        PacketDistributor.sendToPlayersTrackingEntity(entity, payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToAll(MinecraftServer server, T payload) {
        PacketDistributor.sendToAllPlayers(payload);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToServer(T payload) {
        PacketDistributor.sendToServer(payload);
    }
}
