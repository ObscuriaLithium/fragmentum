package dev.obscuria.fragmentum.api.v1.common;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

/**
 * A networking interface for handling custom payload-based packet communication in Fragmentum.
 */
@SuppressWarnings("unused")
public interface FragmentumNetworking
{
    /**
     * Retrieves an {@link IPayloadRegistrar} instance for registering custom payload handlers.
     *
     * @param modId The unique identifier for the mod (namespace).
     * @return An {@link IPayloadRegistrar} instance for registering payloads specific to the mod.
     */
    static IPayloadRegistrar registrar(String modId)
    {
        return V1Common.INSTANCE.payloadRegister(modId);
    }

    /**
     * Replies to the sender of a packet with a custom payload.
     *
     * @param <T>     The type of the custom payload extending {@link CustomPacketPayload}.
     * @param payload The payload to send as a reply.
     */
    static <T extends CustomPacketPayload> void reply(T payload)
    {
        V1Common.INSTANCE.reply(payload);
    }

    /**
     * Sends a custom payload to a specific player on the server.
     *
     * @param <T>     The type of the custom payload extending {@link CustomPacketPayload}.
     * @param player  The {@link ServerPlayer} to send the payload to.
     * @param payload The custom payload to be sent.
     */
    static <T extends CustomPacketPayload> void sendTo(ServerPlayer player, T payload)
    {
        V1Common.INSTANCE.sendTo(player, payload);
    }

    /**
     * Sends a custom payload to all players tracking a specific block position in a world.
     *
     * @param <T>     The type of the custom payload extending {@link CustomPacketPayload}.
     * @param world   The {@link ServerLevel} where the block is located.
     * @param pos     The {@link BlockPos} being tracked.
     * @param payload The custom payload to send.
     */
    static <T extends CustomPacketPayload> void sendToTracking(ServerLevel world, BlockPos pos, T payload)
    {
        V1Common.INSTANCE.sendToTracking(world, pos, payload);
    }

    /**
     * Sends a custom payload to all players tracking a specific entity in the world.
     *
     * @param <T>     The type of the custom payload extending {@link CustomPacketPayload}.
     * @param entity  The {@link Entity} being tracked.
     * @param payload The custom payload to send.
     */
    static <T extends CustomPacketPayload> void sendToTracking(Entity entity, T payload)
    {
        V1Common.INSTANCE.sendToTracking(entity, payload);
    }

    /**
     * Sends a custom payload to all players on the server.
     *
     * @param <T>     The type of the custom payload extending {@link CustomPacketPayload}.
     * @param server  The {@link MinecraftServer} where the players are connected.
     * @param payload The custom payload to send.
     */
    static <T extends CustomPacketPayload> void sendToAll(MinecraftServer server, T payload)
    {
        V1Common.INSTANCE.sendToAll(server, payload);
    }

    /**
     * Sends a custom payload from the client to the server.
     *
     * @param <T>     The type of the custom payload extending {@link CustomPacketPayload}.
     * @param payload The custom payload to send.
     */
    static <T extends CustomPacketPayload> void sendToServer(T payload)
    {
        V1Common.INSTANCE.sendToServer(payload);
    }
}