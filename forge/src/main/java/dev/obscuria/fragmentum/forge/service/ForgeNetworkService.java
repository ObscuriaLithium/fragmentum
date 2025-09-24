package dev.obscuria.fragmentum.forge.service;

import dev.obscuria.fragmentum.forge.registry.ForgeNetworking;
import dev.obscuria.fragmentum.forge.registry.ForgePayloadRegistrar;
import dev.obscuria.fragmentum.network.PayloadRegistrar;
import dev.obscuria.fragmentum.service.NetworkService;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.PacketDistributor;

public final class ForgeNetworkService implements NetworkService
{
    public static final ForgeNetworkService INSTANCE = new ForgeNetworkService();

    private ForgeNetworkService() {}

    @Override
    public PayloadRegistrar payloadRegistrar(String modId)
    {
        return new ForgePayloadRegistrar(modId);
    }

    @Override
    public <T> void reply(T payload)
    {
        if (ForgeNetworking.replyContext == null) throw new IllegalStateException("No context to reply to");
        ForgeNetworking.channelFor(payload).reply(payload, ForgeNetworking.replyContext);
    }

    @Override
    public <T> void sendTo(ServerPlayer player, T payload)
    {
        ForgeNetworking.channelFor(payload).send(PacketDistributor.PLAYER.with(() -> player), payload);
    }

    @Override
    public <T> void sendToTracking(ServerLevel level, BlockPos pos, T payload)
    {
        ForgeNetworking.channelFor(payload).send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(pos)), payload);
    }

    @Override
    public <T> void sendToTracking(Entity entity, T payload)
    {
        ForgeNetworking.channelFor(payload).send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), payload);
    }

    @Override
    public <T> void sendToAll(MinecraftServer server, T payload)
    {
        ForgeNetworking.channelFor(payload).send(PacketDistributor.ALL.noArg(), payload);
    }

    @Override
    public <T> void sendToServer(T payload)
    {
        ForgeNetworking.channelFor(payload).send(PacketDistributor.SERVER.noArg(), payload);
    }
}
