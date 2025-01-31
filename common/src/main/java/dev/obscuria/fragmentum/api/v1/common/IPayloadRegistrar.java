package dev.obscuria.fragmentum.api.v1.common;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.function.BiConsumer;

/**
 * A registrar interface for handling registration of custom packet payloads for both clientbound and serverbound communication.
 */
public interface IPayloadRegistrar
{
    /**
     * Configures the registrar to only accept client-side packet handling.
     * <p>
     * By default, both client and server packet handling are allowed unless restricted.
     */
    void allowClientOnly();

    /**
     * Configures the registrar to only accept server-side packet handling.
     * <p>
     * By default, both client and server packet handling are allowed unless restricted.
     */
    void allowServerOnly();

    /**
     * Registers a clientbound packet payload.
     * <p>
     * This allows a custom payload to be sent from the server to the client with specified serialization, deserialization,
     * and handling logic.
     *
     * @param <T>         The type of the payload extending {@link CustomPacketPayload}.
     * @param clazz       The {@link Class} of the payload to register.
     * @param type        The {@link CustomPacketPayload.Type} representing the packet type.
     * @param streamCodec The {@link StreamCodec} responsible for encoding and decoding the payload for network transmission.
     * @param handler     A {@link BiConsumer} providing the logic to handle the payload when received by a {@link Player}.
     */
    <T extends CustomPacketPayload> void registerClientbound(
            Class<T> clazz,
            CustomPacketPayload.Type<T> type,
            StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
            BiConsumer<Player, T> handler);

    /**
     * Registers a serverbound packet payload.
     * <p>
     * This allows a custom payload to be sent from the client to the server with specified serialization, deserialization,
     * and handling logic.
     *
     * @param <T>         The type of the payload extending {@link CustomPacketPayload}.
     * @param clazz       The {@link Class} of the payload to register.
     * @param type        The {@link CustomPacketPayload.Type} representing the packet type.
     * @param streamCodec The {@link StreamCodec} responsible for encoding and decoding the payload for network transmission.
     * @param handler     A {@link BiConsumer} providing the logic to handle the payload when received by a {@link ServerPlayer}.
     */
    <T extends CustomPacketPayload> void registerServerbound(
            Class<T> clazz,
            CustomPacketPayload.Type<T> type,
            StreamCodec<RegistryFriendlyByteBuf, T> streamCodec,
            BiConsumer<ServerPlayer, T> handler);
}