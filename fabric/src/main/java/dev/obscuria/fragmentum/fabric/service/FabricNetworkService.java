package dev.obscuria.fragmentum.fabric.service;

import dev.obscuria.fragmentum.fabric.registry.FabricNetworking;
import dev.obscuria.fragmentum.fabric.registry.FabricPayloadRegistrar;
import dev.obscuria.fragmentum.network.PayloadRegistrar;
import dev.obscuria.fragmentum.service.NetworkService;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public final class FabricNetworkService implements NetworkService
{
    public static final FabricNetworkService INSTANCE = new FabricNetworkService();

    private FabricNetworkService() {}

    @Override
    public PayloadRegistrar payloadRegistrar(String modId)
    {
        return new FabricPayloadRegistrar(modId);
    }

    @Override
    public <T> void reply(T payload)
    {
        if (FabricNetworking.clientReplySender != null)
        {
            FabricNetworking.clientReplySender.sendPacket(FabricNetworking.serverbound(payload));
        }
        if (FabricNetworking.serverReplySender != null)
        {
            FabricNetworking.serverReplySender.sendPacket(FabricNetworking.clientbound(payload));
        }
    }

    @Override
    public <T> void sendTo(ServerPlayer player, T payload)
    {
        ServerPlayNetworking.send(player, FabricNetworking.clientbound(payload));
    }

    @Override
    public <T> void sendToTracking(ServerLevel level, BlockPos pos, T payload)
    {
        PlayerLookup.tracking(level, pos).forEach(player -> sendTo(player, payload));
    }

    @Override
    public <T> void sendToTracking(Entity entity, T payload)
    {
        PlayerLookup.tracking(entity).forEach(player -> sendTo(player, payload));
    }

    @Override
    public <T> void sendToAll(MinecraftServer server, T payload)
    {
        PlayerLookup.all(server).forEach(player -> sendTo(player, payload));
    }

    @Override
    public <T> void sendToServer(T payload)
    {
        ClientPlayNetworking.send(FabricNetworking.serverbound(payload));
    }
}
