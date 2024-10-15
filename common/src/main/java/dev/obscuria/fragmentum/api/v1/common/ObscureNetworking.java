package dev.obscuria.fragmentum.api.v1.common;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public interface ObscureNetworking
{
    static <T extends CustomPacketPayload> void
    registerClientbound(String modId,
                        Class<T> clazz,
                        CustomPacketPayload.Type<T> type,
                        StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
                        BiConsumer<Player, T> handler)
    {
        V1Common.INSTANCE.registerClientbound(modId, clazz, type, streamCodec, handler);
    }

    static <T extends CustomPacketPayload> void
    registerServerbound(String modId,
                        Class<T> clazz,
                        CustomPacketPayload.Type<T> type,
                        StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
                        BiConsumer<ServerPlayer, T> handler)
    {
        V1Common.INSTANCE.registerServerbound(modId, clazz, type, streamCodec, handler);
    }

    static <T extends CustomPacketPayload> void reply(T payload)
    {
        V1Common.INSTANCE.reply(payload);
    }

    static <T extends CustomPacketPayload> void sendTo(ServerPlayer player,
                                                       T payload)
    {
        V1Common.INSTANCE.sendTo(player, payload);
    }

    static <T extends CustomPacketPayload> void sendToTracking(ServerLevel world,
                                                               BlockPos pos,
                                                               T payload)
    {
        V1Common.INSTANCE.sendToTracking(world, pos, payload);
    }

    static <T extends CustomPacketPayload> void sendToTracking(Entity entity,
                                                               T payload)
    {
        V1Common.INSTANCE.sendToTracking(entity, payload);
    }

    static <T extends CustomPacketPayload> void sendToAll(MinecraftServer server,
                                                          T payload)
    {
        V1Common.INSTANCE.sendToAll(server, payload);
    }

    static <T extends CustomPacketPayload> void sendToServer(T payload)
    {
        V1Common.INSTANCE.sendToServer(payload);
    }
}
