package dev.obscuria.fragmentum.neoforge.service;

import dev.obscuria.fragmentum.neoforge.registry.NeoForgeNetworking;
import dev.obscuria.fragmentum.neoforge.registry.NeoForgePayloadRegistrar;
import dev.obscuria.fragmentum.service.NetworkService;
import dev.obscuria.fragmentum.v2.api.common.network.PayloadRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.network.PacketDistributor;

public final class NeoForgeNetworkService implements NetworkService {

    public static final NeoForgeNetworkService SHARED = new NeoForgeNetworkService();

    @Override public PayloadRegistrar registrar(String modId) {
        return new NeoForgePayloadRegistrar(modId);
    }

    @Override public <T extends CustomPacketPayload> void reply(T payload) {
        if (NeoForgeNetworking.replyConsumer == null) throw new IllegalStateException("No context to reply to");
        NeoForgeNetworking.replyConsumer.accept(payload);
    }

    @Override public <T extends CustomPacketPayload> void sendTo(ServerPlayer player, T payload) {
        PacketDistributor.sendToPlayer(player, payload);
    }

    @Override public <T extends CustomPacketPayload> void sendToAllTracking(ServerLevel level, BlockPos pos, T payload) {
        PacketDistributor.sendToPlayersTrackingChunk(level, ChunkPos.containing(pos), payload);
    }

    @Override public <T extends CustomPacketPayload> void sendToAllTracking(Entity entity, T payload) {
        PacketDistributor.sendToPlayersTrackingEntity(entity, payload);
    }

    @Override public <T extends CustomPacketPayload> void sendToAll(MinecraftServer server, T payload) {
        PacketDistributor.sendToAllPlayers(payload);
    }

    @Override public <T extends CustomPacketPayload> void sendToServer(T payload) {
        ClientPacketDistributor.sendToServer(payload);
    }

    private NeoForgeNetworkService() {}
}
